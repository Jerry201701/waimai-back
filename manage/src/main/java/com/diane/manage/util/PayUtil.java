package com.diane.manage.util;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PayUtil {


    private static final String PROD_PAY_URL="xxxxxxxxxxxxxxx";
    private static final String QUERY_ORDER_PAY_STATUS="xxxxxxxxxxxxxxxxx";
    private static final String REFUND_URL="xxxxxxxxxxxxxxxxxxxxxxxx";
    private static  final String  QUERY_REFUND_URL="xxxxxxxxxxxxxxxxxxxxxxx";

    /*
    预下单接口
     */
    public static JSONObject  payOrderAmount(String command,
                                             String appid,
                                             String openid,
                                             BigDecimal amount,
                                             String subject,
                                             String orderNumber,
                                             String notifyUrl,
                                             String paysucUrl,
                                             String payfailUrl){
        Map<String,Object> parms=new HashMap<>();
        parms.put("command",command);
        parms.put("paymentway",2);
        parms.put("sub_app_id",appid);
        parms.put("local_order_no",orderNumber);
        parms.put("open_id",openid);
        parms.put("subject",subject);
        parms.put("totalamount",amount);
        parms.put("notify_url",notifyUrl);
        parms.put("paysuc_url",paysucUrl);
        parms.put("payfail_url",payfailUrl);
        //  String sign= CommonUtil.signUtil(parms);
        // parms.put("sign",sign);
       // Map<String,Map<String,Object>> request=new HashMap<>();
      //  request.put("dwkey",parms);
     //   String map= JSONUtils.toJSONString(request);
       // String result=  HttpClientUtil.doPostJson(PROD_PAY_URL,map);
      //  JSONObject jsonObject= JSONObject.parseObject(result);
      //  System.out.println(jsonObject);

        return payCenter(PROD_PAY_URL,parms);
    }

    /*
    查询订单支付情况
     */
    public static JSONObject getOrderPayStatus(String orderNumber){
        Map<String,Object> map=new HashMap<>();
        map.put("command","open.pay.query");
        map.put("local_order_no",orderNumber);
       return payCenter(QUERY_ORDER_PAY_STATUS,map);

    }
    /*
    向支付平台发请求
     */

    private static  JSONObject payCenter(String url,Map<String,Object> map){
        map.put("merid",351);
        map.put("mernum",351);
        map.put("platformid",2);
        map.put("timestamp",System.currentTimeMillis());
        Map<String,Map<String,Object>> request=new HashMap<>();
        request.put("dwkey",map);
        String parameter= JSONUtils.toJSONString(request);
        JSONObject jsonObject= JSONObject.parseObject(HttpClientUtil.doPostJson(url,parameter));
        return jsonObject;
    }

    /*
    退款接口
    退款渠道 paymentway: 1 支付宝 2 微信
     */
    public static JSONObject refundToCustomer(String orderNumber,BigDecimal refundAmount,String reason,String refundNumber){
        Map<String,Object> refund=new HashMap<>();
        refund.put("command","open.pay.refund");
        refund.put("refundtype",2);
        refund.put("local_refund_no",refundNumber);
        refund.put("local_order_no",orderNumber);
        refund.put("refund_amount",refundAmount);
        refund.put("reason",reason);
        return payCenter(REFUND_URL,refund);
    }

    /*
    退款订单查询
     */
    public static  JSONObject queryRefundOrder(String orderNumber,String refundNumber){
        Map<String,Object>query=new HashMap<>();
        query.put("command","open.pay.refundrecords");
        query.put("local_order_no",orderNumber);
        query.put("local_refund_no",refundNumber);
        return payCenter(QUERY_REFUND_URL,query);
    }








}
