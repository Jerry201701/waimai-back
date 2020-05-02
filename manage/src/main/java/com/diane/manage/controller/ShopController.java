package com.diane.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.diane.common.http.HttpResult;
import com.diane.common.page.ColumnFilter;
import com.diane.common.page.MybatisPageHelper;
import com.diane.common.page.PageRequest;
import com.diane.common.page.PageResult;
import com.diane.manage.constants.DeliveryStatusEnum;
import com.diane.manage.model.*;
import com.diane.manage.service.*;
import com.diane.manage.util.PasswordUtils;
import com.diane.manage.util.PayUtil;
import com.diane.manage.util.WithdrawalUtil;
import com.diane.manage.vo.PasswordVo;
import com.diane.manage.vo.PwdCheckVo;
import com.diane.manage.vo.activity.FullReductionVo;
import com.diane.manage.vo.comment.CommentPageResultVo;
import com.diane.manage.vo.comment.CommentQuery;
import com.diane.manage.vo.order.DeliveryOrderStatusVo;
import com.diane.manage.vo.order.OrderManagementVo;
import com.diane.manage.vo.order.OrderQueryInfo;
import com.diane.manage.vo.order.PayResultDetail;
import com.diane.manage.vo.pay.QueryRefundResultVo;
import com.diane.manage.vo.shop.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private DeliveryOrderService deliveryOrderService;
    @Autowired
    private DeliveryCommentService deliveryCommentService;
    @Autowired
    private DeliveryShopService deliveryShopService;
    @Autowired
    private GoodInfoService goodInfoService;
    @Autowired
    private ShopWithdrawService shopWithdrawService;
    @Autowired
    private DeliveryRecordService deliveryRecordService;
    @Autowired
    private FullReductionService fullReductionService;
    @Autowired
    private SubAdminService subAdminService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CompanyInfoService companyInfoService;
    @Autowired
    private SettlingModelService settlingModelService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private ComplaintDeliveryService complaintDeliveryService;
    @Autowired
    private WhiteListService whiteListService;



    @RequestMapping(value = "/info/edit")
    public HttpResult saveShopInfo(@RequestBody ShopInfo shopInfo){
        if (shopInfo.getFlag()!=null){
        if (shopInfo.getFlag()){
            return HttpResult.ok( shopService.deleteSingleShopInfo(shopInfo));
        }
        }
//        if (shopInfo.getShopStatus()==3){
//           shopService.saveShopInfo(shopInfo);
//            return HttpResult.ok("正在审核中");
//        }

        if (shopInfo.getId()==null||shopInfo.getId()==0){
        return HttpResult.ok(shopService.saveShopInfo(shopInfo));
        }
        if (shopInfo.getPass()!=null){
        if (shopInfo.getPass()){
            shopInfo.setShopStatus(1);
        }else {
            shopInfo.setShopStatus(4);
        }
        }

        return HttpResult.ok(shopService.updateShopInfo(shopInfo));

    }

   // @PreAuthorize("hasAuthority('account:shop:view')")
//    @PostMapping(value="/findPage")
//    public HttpResult findPage(@RequestBody QueryShopVo queryShopVo) {
//        ColumnFilter columnFilter = pageRequest.getColumnFilter("label");
//        String status=columnFilter.getValue();
//        if (status.isEmpty()){
//            return HttpResult.ok(shopService.findPage(pageRequest));
//        }
//
//        return HttpResult.ok(shopService.findPage(pageRequest));
//    }

   // @PreAuthorize("hasAuthority('account:shop:view')")
    @PostMapping(value="/findPage")
    public HttpResult listShopByPage(@RequestBody QueryShopVo queryShopVo){
        PageHelper.startPage(queryShopVo.getPageNum(),queryShopVo.getPageSize());

        PageResult pageResult=new PageResult();
        Page<ShopInfo>page=shopService.listShopByPage(queryShopVo);
        pageResult.setContent(page);
        pageResult.setPageNum(queryShopVo.getPageNum());
        pageResult.setTotalPages(10);
        pageResult.setPageSize(10);
        pageResult.setTotalSize(100);

        return HttpResult.ok(pageResult);
    }





   // @PreAuthorize("hasAuthority('account:shop:view')")
    @PostMapping(value="/page/company")
    public HttpResult findCompanyPageByUser(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(shopService.findPageByCompany(pageRequest));
    }

     @PostMapping(value = "/delete")
    public HttpResult deleteShopInfo(@RequestBody  List<ShopInfo> list){
         System.out.println(list.size());

         return HttpResult.ok(shopService.deleteShopInfo(list));
    }

    @PostMapping(value = "/list")
    public HttpResult listShopByConditions(@RequestBody ShopInfo shopInfo){
        return HttpResult.ok(shopService.listShopByConditions(shopInfo));
    }
    @GetMapping(value = "/info/detail")
    public HttpResult showDetailById(@RequestParam(value = "shopId") Long shopId){

        return HttpResult.ok(shopService.getShopDetails(shopId));

    }

    @PostMapping(value = "/order/list")
    public  HttpResult listOrderByShop(@RequestBody OrderQueryInfo queryInfo){
        PageHelper.startPage(queryInfo.getPageNum(),queryInfo.getPageSize());
        return HttpResult.ok(deliveryShopService.listShopOrder(queryInfo));
    }
    @GetMapping(value = "/order/detail")
    public HttpResult shopOrderDetail(@RequestParam(value = "orderNumber")String orderNumber){


        return HttpResult.ok(deliveryShopService.deliveryOrderDetailByNumber(orderNumber));
      //  return HttpResult.error("订单查询失败");
    }
    /*
    管理订单
     */
    @PostMapping(value = "/order/edit")
    public HttpResult manageOrder(@RequestBody OrderManagementVo orderManagementVo){
        String orderNumber=orderManagementVo.getOrderNumber();
        DeliveryShop deliveryShop=deliveryOrderService.getPayAmountByOrderNumber(orderNumber);
        if(!deliveryShop.getAutoReceipt()){
            if (orderManagementVo.getHandleStatus()==2){
                deliveryShop.setHandleStatus(2);
                deliveryOrderService.changeOrderStatus(deliveryShop);
                return HttpResult.ok("接单成功");
            }
        }
        //订单制作完成
        if (orderManagementVo.getHandleStatus()==4||orderManagementVo.getHandleStatus()==5){
            /*
            门店编辑订单为5，需要计算配送费
             */
//            if(orderManagementVo.getHandleStatus()==5){
//                SettlingModel settlingModel=settlingModelService.findSettlementByShop(orderManagementVo.getShopId());
//                deliveryShop.setDeliveryFee(settlingModel.getFixedAmount());
//            }
          //  deliveryShop.setDeliveryFee(1);
            deliveryShop.setHandleStatus(orderManagementVo.getHandleStatus());
            int i=deliveryOrderService.changeOrderStatus(deliveryShop);
           if (i==1){
               return HttpResult.ok("订单修改成功");
           }
          //  return HttpResult.error(DeliveryStatusEnum.HANDLE_FAIL.getCode(),DeliveryStatusEnum.HANDLE_FAIL.getStatus());
        }
        //无效订单的处理
        if (orderManagementVo.getHandleStatus()==3){
            Integer refundAmount=deliveryShop.getPayAmount()+deliveryShop.getDeliveryFee();

           int i= deliveryOrderService.refundManagement(orderNumber,"无效订单",refundAmount);
            if (i==1){
                return HttpResult.ok("无效订单处理成功");
            }
            return HttpResult.error("无效订单处理失败");
        }
            //用户不满意申请退款
        if (orderManagementVo.getHandleStatus()==6){

            if (deliveryShop.getPayStatus()==1){
                return HttpResult.error("还未支付");

            }
            if (deliveryShop.getPayStatus()==7){
                return HttpResult.error("已经退款");
            }

            deliveryShop.setRefundAmount(orderManagementVo.getRefundAmount().intValue());
            deliveryShop.setRefundNumber(orderNumber);
            if (new BigDecimal(deliveryShop.getAmount()).compareTo(orderManagementVo.getRefundAmount())==-1){
                return HttpResult.error("退款金额超过限制");
            }
            int i= deliveryOrderService.refundManagement(orderNumber,"无效订单",deliveryShop.getAmount());
            if (i==1){

                deliveryShop.setPayStatus(7);
                deliveryOrderService.changeOrderStatus(deliveryShop);
                return HttpResult.ok("申请退款成功");
            }
            deliveryShop.setPayStatus(8);
            deliveryOrderService.changeOrderStatus(deliveryShop);
            return HttpResult.error("申请退款失败");


        }
         return HttpResult.error("处理订单失败");

    }

    @PostMapping(value = "/comment/list")
    public HttpResult commentInfoList(@RequestBody CommentQuery commentQuery){
        PageHelper.startPage(commentQuery.getPageNum(),commentQuery.getPageSize());
        Page<CommentPageResultVo> page=deliveryCommentService.shopCommentPageInfo(commentQuery);
        return HttpResult.ok(page);
    }

    @PostMapping(value = "/comment/reply")
    public HttpResult commentReply(@RequestBody CommentQuery commentQuery){
        int i=deliveryCommentService.commentReply(commentQuery);
        if (i==1){
        return  HttpResult.ok();

        }
        return HttpResult.error("回复失败");
    }

    @GetMapping(value = "/refund/query")
    public void testRefundQuery(@RequestParam(value = "orderNumber") String orderNumber){
      // JSONObject jsonObject= PayUtil.queryRefundOrder(orderNumber);
       // System.out.println(jsonObject);
        JSONObject queryResult=PayUtil.queryRefundOrder(orderNumber,orderNumber.concat("_refund"));
        List<QueryRefundResultVo>list=JSONObject.parseArray(queryResult.getString("body"),QueryRefundResultVo.class);
        QueryRefundResultVo queryRefundResultVo = list.get(0);
        System.out.println(queryRefundResultVo.getRefundStatus());
    }
    @GetMapping(value = "/check")
    public HttpResult checkShop(){


        return  null;
    }
    @PostMapping(value = "/cone")
    public HttpResult copyGood(@RequestBody ShopReqVo shopReqVo){

        return  HttpResult.ok(goodInfoService.copyShopGood(shopReqVo));
    }

    @PostMapping(value = "/withdraw/apply")
    public HttpResult withdrawApply(@RequestBody ShopWithdraw shopWithdraw){
        if (shopWithdraw.getMoney()<100){
            return HttpResult.error("提现低于最低限制1元");
        }
        shopWithdraw.setType(1);
        int i=shopWithdrawService.insertSelective(shopWithdraw);
        if (i==2){
            return HttpResult.error("余额不足");
        }

        return HttpResult.ok(i);

    }
    @PostMapping(value = "/withdraw/list")
    public HttpResult withdrawPage(@RequestBody WithdrawVo withdrawVo){

        return HttpResult.ok(shopWithdrawService.findPageByCompany(withdrawVo));
    }
    @PostMapping(value = "/withdraw/feedback")
    public HttpResult withdrawFeedback(@RequestBody ShopWithdraw shopWithdraw){
        return HttpResult.ok(shopWithdrawService.updateByPrimaryKeySelective(shopWithdraw));

    }
    @GetMapping(value = "/order/reminder")
    public HttpResult orderRemindList(@RequestParam(value = "shopId")Long shopId,
                                        @RequestParam(value = "companyId")Long companyId){

    return HttpResult.ok(deliveryRecordService.orderRemindList(shopId,companyId));

    }
    @PostMapping(value = "/active/edit")
    public HttpResult activityEdit(@RequestBody FullReduction fullReduction){
        if (fullReduction.getFlag()==null&&fullReduction.getActiveId()==null){
            return HttpResult.ok(fullReductionService.insertSelective(fullReduction));
        }
        if (fullReduction.getFlag()==null&&fullReduction.getActiveId()!=null){
            return HttpResult.ok(fullReductionService.updateByPrimaryKeySelective(fullReduction));
        }
        if (fullReduction.getFlag()){
            return HttpResult.ok(fullReductionService.removeRecord(fullReduction.getActiveId()));
        }
        return HttpResult.error("修改失败");

    }
    @PostMapping(value = "/active/list")
    public  HttpResult listActivity(@RequestBody FullReductionVo fullReductionVo){
        return HttpResult.ok(fullReductionService.listFullReductionByShop(fullReductionVo.getShopId()));
    }

    @PostMapping(value = "/subadmin/edit")
    public HttpResult subAdminEdit(@RequestBody SubAdmin subAdmin){
            SysUser sysUser=new SysUser();
            String salt =PasswordUtils.getSalt();
            sysUser.setSalt(salt);
        System.out.println("管理员注册密码"+subAdmin.getPassword());
        if (subAdmin.getAdminId()==null||subAdmin.getAdminId()==0){
            if (sysUserService.findByMobilAndType(subAdmin.getUsername(),3)!=null){
                return HttpResult.error("用户名已存在!");
            }

            sysUser.setName(subAdmin.getUsername());
            sysUser.setMobile(subAdmin.getUsername());
            sysUser.setPassword(PasswordUtils.encode(subAdmin.getPassword(),salt ));
            sysUser.setCreateTime(new Date());
            sysUser.setRegisterType(3);

            Long userId=sysUserService.addUserInfo(sysUser);
            if (userId==null){
                return HttpResult.error(500,"服务器异常");
            }
            subAdmin.setCreateTime(new Timestamp(System.currentTimeMillis()));
            subAdmin.setUserId(userId);
            subAdmin.setFlag(false);
            return HttpResult.ok(subAdminService.insertSelective(subAdmin));
        }

        if (subAdmin.getFlag()==null||!subAdmin.getFlag()){
        sysUser.setId(subAdmin.getUserId());
        if (subAdmin.getName()!=null){
            sysUser.setName(subAdmin.getName());
        }
        if(subAdmin.getUsername()!=null){
            sysUser.setMobile(subAdmin.getUsername());
        }
        sysUserService.save(sysUser);
        }
        return HttpResult.ok(HttpResult.ok(subAdminService.updateByPrimaryKeySelective(subAdmin)));
    }

    @GetMapping(value = "/subadmin/list")
    public HttpResult subAdminList(@RequestParam(value = "shopId") Long shopId,
                                   @RequestParam(value = "companyId")Long companyId
    ){

        return HttpResult.ok(subAdminService.listSubAdmin(companyId,shopId));
    }


    @PostMapping(value = "/changepwd")
    public HttpResult changePassword(@RequestBody PasswordVo passwordVo){
        Long userId=passwordVo.getUserId();
        PwdCheckVo pwdCheckVo=sysUserService.findPasswordInfo(userId);
        /*
        Long userId=passwordVo.getUserId();
        if (userId==null){
            userId=    sysUserService.findUserId(passwordVo);
        }
        SysUser sysUser=sysUserService.selectByPrimaryKey(userId);
         */
        if (passwordVo.getType()==1){
            if (!PasswordUtils.matches(pwdCheckVo.getSalt(), passwordVo.getOldPassword(), pwdCheckVo.getPassword())) {
                return HttpResult.error("原密码不正确");
            }
            return HttpResult.ok(sysUserService.changePassword(passwordVo.getPassword(),userId));

        }
        if (passwordVo.getType()==2){
            if (pwdCheckVo.getAdminId()!=null){
                if (passwordVo.getOldOperatorPassword().equals(pwdCheckVo.getAdminOperatorPassword())){

                SubAdmin subAdmin=new SubAdmin();
                subAdmin.setAdminId(pwdCheckVo.getAdminId());
                subAdmin.setOperatorPassword(passwordVo.getOperatorPassword());
                return HttpResult.ok(subAdminService.updateByPrimaryKeySelective(subAdmin));
                }
                return  HttpResult.error("原操作密码不正确");
            }
            if (pwdCheckVo.getCompanyId()!=null){
                if (passwordVo.getOldOperatorPassword().equals(pwdCheckVo.getCompanyOperatorPassword())){
                CompanyInfo companyInfo=new CompanyInfo();
                companyInfo.setOperatePassword(passwordVo.getOperatorPassword());
                companyInfo.setId(passwordVo.getCompanyId());
                return HttpResult.ok(companyInfoService.updateCompanyInfo(companyInfo));
                }
                return HttpResult.error("原操作密码不正确");
            }
        }
        return HttpResult.error("密码修改失败");
    }


    @PostMapping(value = "/findpwd")
    public HttpResult findPassword(@RequestBody PasswordVo passwordVo){
        String code ="123456";
            SysUser sysUser =sysUserService.findByMobilAndType(passwordVo.getPhone(),passwordVo.getUserType());
        if (passwordVo.getCode().equals(code)&&passwordVo.getType()==1){
            return HttpResult.ok(sysUserService.changePassword(passwordVo.getPassword(),sysUser.getId()));
        }
        if (passwordVo.getCode().equals(code)&&passwordVo.getType()==2){
           PwdCheckVo pwdCheckVo= sysUserService.findPasswordInfo(sysUser.getId());

           if (pwdCheckVo.getCompanyId()!=null){
               CompanyInfo companyInfo=new CompanyInfo();
               companyInfo.setOperatePassword(passwordVo.getPassword());
               companyInfo.setId(pwdCheckVo.getCompanyId());
               return HttpResult.ok(companyInfoService.updateCompanyInfo(companyInfo));

             //  return HttpResult.ok(pwdCheckVo.getCompanyOperatorPassword());
            //   return HttpResult.ok(pwdCheckVo.getCompanyOperatorPassword());

           }
           if (pwdCheckVo.getAdminId()!=null){
               SubAdmin subAdmin=new SubAdmin();
               subAdmin.setAdminId(pwdCheckVo.getAdminId());
               subAdmin.setOperatorPassword(passwordVo.getPassword());
               return HttpResult.ok(subAdminService.updateByPrimaryKeySelective(subAdmin));
              // return HttpResult.ok(pwdCheckVo.getAdminOperatorPassword());
           }


        }
        return HttpResult.error("验证码错误");
    }


    @PostMapping(value = "/password/check")
    public HttpResult checkOperatorPassword(@RequestBody PasswordVo passwordVo){
        PwdCheckVo pwdCheckVo=sysUserService.findPasswordInfo(passwordVo.getUserId());
        if (pwdCheckVo.getCompanyId()!=null){
            if (pwdCheckVo.getCompanyOperatorPassword().equals(passwordVo.getPassword())){
                return HttpResult.ok("验证成功");
            }

        }
        if (pwdCheckVo.getAdminId()!=null){
            if (pwdCheckVo.getAdminOperatorPassword().equals(passwordVo.getPassword())){
                return HttpResult.ok("验证成功");
            }
        }
        return HttpResult.error("验证失败");
    }
    @GetMapping(value = "/delivery/list")
    public HttpResult deliveryList(@RequestParam(value = "regionId")Long regionId,
                                   @RequestParam(value = "shopId")Long shopId,
                                   @RequestParam(value = "type")Integer type,
                                   @RequestParam(value = "pageNum")Integer pageNum,
                                   @RequestParam(value = "pageSize")Integer pageSize,
                                   @RequestParam(value = "keyWord")String keyWord){
        PageHelper.startPage(pageNum,pageSize);

        if (type==2){
            return HttpResult.ok(whiteListService.findBlackListByShop(shopId));

        }

        return  HttpResult.ok(deliveryService.findDeliveryByRegion(regionId,keyWord,type,shopId));
    }
    @PostMapping(value = "/delivery/complain")
    public HttpResult complaintDelivery(@RequestBody ComplaintDelivery complaintDelivery){
        complaintDelivery.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return HttpResult.ok(complaintDeliveryService.addComplaintDelivery(complaintDelivery));
    }
    @GetMapping(value = "/delivery/roster")
    public HttpResult whiteList(@RequestParam(value = "deliveryId")Long deliveyId,
                                @RequestParam(value = "shopId")Long shopId,
                                @RequestParam(value = "type")Integer type){
        WhiteList whiteList=new WhiteList();
        whiteList.setDeliveryId(deliveyId);
        whiteList.setShopId(shopId);
        if (type==2){
            whiteList.setFlag(true);
        }
        if (type==1){
            whiteList.setFlag(false);
        }
        Integer number=whiteListService.findSingleWhiteList(whiteList.getShopId(),whiteList.getDeliveryId());
        if (number>0){
            return HttpResult.ok(whiteListService.updateWhiteList(whiteList));
        }

        return HttpResult.ok(whiteListService.addWhiteList(whiteList));
    }

    @GetMapping(value = "/qrimg")
    public HttpResult getShopCode(@RequestParam(value = "shopId")Long shopId){
        Map<String,String>map=new HashMap<>();
        map.put("qrPicUrl",shopService.findShopCode(shopId));
        return HttpResult.ok(map);

    }
    @PostMapping(value = "/order/dispatch")
    public HttpResult deliveryDispatch(@RequestBody ShopDispatchVo shopDispatchVo){
        return HttpResult.ok(deliveryOrderService.dispatchDelivery(shopDispatchVo));


    }

    @PostMapping(value = "/statistics")
    public HttpResult shopIncomeStatistics(@RequestBody ShopStatisticsVo shopStatisticsVo){


        return HttpResult.ok(deliveryOrderService.shopStatistics(shopStatisticsVo));
    }
    @GetMapping(value = "/basic/info")
    public HttpResult showShopBasicInfo(@RequestParam(value = "shopId")Long shopId){




        return HttpResult.ok(shopService.showShopBasicInfo(shopId));
    }




}
