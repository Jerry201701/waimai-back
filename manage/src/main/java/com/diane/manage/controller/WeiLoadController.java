package com.diane.manage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.diane.common.http.HttpResult;
import com.diane.manage.model.CompanyInfo;
import com.diane.manage.model.SubAdmin;
import com.diane.manage.model.SysUser;
import com.diane.manage.model.WeiUserInfo;
import com.diane.manage.service.CompanyInfoService;
import com.diane.manage.service.SubAdminService;
import com.diane.manage.service.SysUserService;
import com.diane.manage.service.WeiUserInfoService;
import com.diane.manage.util.DecryptionUtil;
import com.diane.manage.util.HttpClientUtil;
import com.diane.manage.util.PasswordUtils;
import com.diane.manage.util.RedisUtil;
import com.diane.manage.vo.DecodePhoneInfo;
import com.diane.manage.vo.PasswordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/wei")
public class WeiLoadController {

    // 请求的网址
    private static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
  //商户appid和secret
    private static final String WX_LOGIN_SHOP_APPID = "xxxxxxxxxxxxxx";
    private static final String WX_LOGIN_SHOP_SECRET = "xxxxxxxxxxxxxxxxxx";
    //用户的appid和secret
    private static final String WX_LOGIN__CUSTOMER_APPID = "xxxxxxxxxxxxxxxxx";
    private static final String WX_LOGIN_CUSTOMER_SECRET = "xxxxxxxxxxxxx";
    //公众号
    private static final String WX_LOGIN__PUBLIC_APPID = "xxxxxxxxxxxxxx";
    private static final String WX_LOGIN_PUBLICE_SECRET = "xxxxxxxxxxxxxxx";
    //配送
    private static final String WX_LOGIN__DELIVERY_APPID = "xxxxxxxxxxxxxxxxxxxx";
    private static final String WX_LOGIN_DELIVERY_SECRET = "xxxxxxxxxxxxxxxxxxxxx";

    private static final String WX_LOGIN_GRANT_TYPE = "authorization_code";

    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private WeiUserInfoService weiUserInfoService;


    /*
    获取用户唯一openid
    */
    @GetMapping(value = "/userinfo/openid")
    public HttpResult weiLogin(@RequestParam(value = "code") String code,
    @RequestParam(value = "type")Integer type) {
        if (type==null){
            return HttpResult.error("角色不明");
        }

        // 配置请求参数
        Map<String, String> param = new HashMap<>();

        if (type==1){
            param.put("appid", WX_LOGIN__CUSTOMER_APPID);
            param.put("secret", WX_LOGIN_CUSTOMER_SECRET);
        }
        if (type==2){
            param.put("appid", WX_LOGIN_SHOP_APPID);
            param.put("secret", WX_LOGIN_SHOP_SECRET);
        }
        if (type==4){
            param.put("appid", WX_LOGIN__PUBLIC_APPID);
            param.put("secret", WX_LOGIN_PUBLICE_SECRET);
        }
        if (type==3){
            param.put("appid", WX_LOGIN__DELIVERY_APPID);
            param.put("secret", WX_LOGIN_DELIVERY_SECRET);
        }

        param.put("js_code", code);
        param.put("grant_type", WX_LOGIN_GRANT_TYPE);
        // 发送请求

        String wxResult = HttpClientUtil.doGet(WX_LOGIN_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        String openid = jsonObject.get("openid").toString();
        System.out.println(openid);
        String sessionKey = jsonObject.get("session_key").toString();

       // redisUtil.set("sessionKey",sessionKey);

        Map<String, String> result = new HashMap<>();
        result.put("session_key", sessionKey);
         result.put("openId", openid);

        HttpResult httpResult=new HttpResult();
        httpResult.setData(result);
        return httpResult;


    }

    /*
    解密电话号码
     */
    @Transactional
    @PostMapping(value = "/decode/phone")
    public HttpResult decodeWxAppPhone(@RequestBody DecodePhoneInfo decodePhoneInfo) {

        try {
           // System.out.println("解密手机号");
          //  System.out.println("redis过期时间："+redisUtil.getExpire("sessionKey"));
          //  String key=redisUtil.get("sessionKey").toString();
            String phoneInfo = DecryptionUtil.getPhoneNumber(decodePhoneInfo.getEncryptedData(),decodePhoneInfo.getSessionKey(), decodePhoneInfo.getIv());
            JSONObject info= JSON.parseObject(phoneInfo);
           // UserInfo userInfo=new UserInfo();
          //  userInfo.setLoginPhone(info.getString("phoneNumber"));

//            ResultVo resultVo=new ResultVo();
//            resultVo.setCode(userInfoService.saveUserInfo(userInfo));
//            resultVo.setData(userInfo);

            return HttpResult.ok(info);
        } catch (Exception e) {
            //log.error("微信小程序手机号码解密异常，信息如下:", e);
            return null;
        }
    }
    /*
    获取用户unionid
     */
    @PostMapping(value = "/union/id")
    public HttpResult getUnionId(@RequestBody DecodePhoneInfo decodePhoneInfo){
      //  weiLogin(decodePhoneInfo.getCode(),decodePhoneInfo.getType());
      //  String key=redisUtil.get("sessionKey").toString();
     //   System.out.println(key);
        String phoneInfo = DecryptionUtil.getPhoneNumber(decodePhoneInfo.getEncryptedData(),decodePhoneInfo.getSessionKey(), decodePhoneInfo.getIv());
        JSONObject info=JSON.parseObject(phoneInfo);
        System.out.println(info);
        return HttpResult.ok(info);
    }
    @GetMapping(value = "/test")
    public String weiRequestTest(){



        return "请求成功";
    }

    @PostMapping(value = "/supply/user/data")
    public HttpResult supplyUserData(@RequestBody WeiUserInfo weiUserInfo){


        return HttpResult.ok(weiUserInfoService.addUserInfo(weiUserInfo));
    }

    @GetMapping(value = "/find/user/info")
    public HttpResult findWeiUserInfo(@RequestParam(value = "openid")String openid){


        return HttpResult.ok(weiUserInfoService.findUserInfo(openid));
    }







}
