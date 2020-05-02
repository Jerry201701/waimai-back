package com.diane.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.diane.common.http.HttpResult;
import com.diane.manage.util.HttpClientUtil;
import com.diane.manage.util.WeiUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/public")
public class WeiPublicController {
        private static  final String WEI_PUBLIC_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxd8c6c0d9d5a935e8&secret=78b6630c279a08998d4e02531b88d52a";
    private static final String  WEI_OAUTH_URL="https://api.weixin.qq.com/sns/oauth2/access_token";
    private static  final String WEI_CODE_URL="https://open.weixin.qq.com/connect/oauth2/authorize";

    private static  final  String WEI_PUBLIC_APPID="xxxxxxxxxxxxxxx";
    private static  final String WEI_PUBLIC_SECRET="xxxxxxxxxxxxxxxxxxxx";
    @GetMapping(value = "/verify")
    public void publicVerify(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam(value = "signature", required = true) String signature,
                             @RequestParam(value = "timestamp", required = true) String timestamp,
                             @RequestParam(value = "nonce", required = true) String nonce,
                             @RequestParam(value = "echostr", required = true) String echostr){
        try {
            System.out.println("验证");
            if (WeiUtils.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                System.out.println("这里存在非法请求！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @GetMapping(value = "/test")
    public  void test(HttpServletRequest request,
                      HttpServletResponse response){
        try {
            PrintWriter out = response.getWriter();
            out.print("success");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

//    @GetMapping(value = "/oauth")
//    public HttpResult getOAuth(HttpServletRequest request, HttpServletResponse response) {
//
//        String code = request.getParameter("code");//获取微信服务器授权返回的code值
//        String state = request.getParameter("state");//验证是否来自微信重定向的请求
//        Map<String, String> param = new HashMap<>();
//        param.put("appid", WEI_PUBLIC_APPID);
//        param.put("secret", WEI_PUBLIC_SECRET);
//        param.put("code", code);
//        param.put("grant_type", "authorization_code");
//        try {
//            if (state.equals("STATE")) {
//
//                String result = HttpClientUtil.doGet(WEI_OAUTH_URL, param);
//                JSONObject jsonObject = JSONObject.parseObject(result);
//                System.out.println(jsonObject);
//                return HttpResult.ok(jsonObject);
//
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return HttpResult.error(200,"授权失败");
//    }


    @GetMapping(value = "/oauth")
    public ModelAndView getOAuth(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");//获取微信服务器授权返回的code值
        String state = request.getParameter("state");//验证是否来自微信重定向的请求
        System.out.println(code);
        System.out.println(state);
        Map<String, String> param = new HashMap<>();
        param.put("appid", WEI_PUBLIC_APPID);
        param.put("secret", WEI_PUBLIC_SECRET);
        param.put("code", code);
        param.put("grant_type", "authorization_code");
        try {

            String result = HttpClientUtil.doGet(WEI_OAUTH_URL, param);
            JSONObject jsonObject = JSONObject.parseObject(result);
            System.out.println(jsonObject);
            String redirect=state.concat("?openid=").concat(jsonObject.getString("openid"));
            return new ModelAndView(new RedirectView(redirect));


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/code")
    public ModelAndView getOpenid(@RequestParam(value = "returnUrl") String returnUrl){
        String url=WEI_CODE_URL.concat("?appid=").concat(WEI_PUBLIC_APPID).concat("&redirect_uri=")
                .concat("https%3A%2F%2Fsc.51diane.com%2Foauth")//urldecode域名编码上面的/oauth地址编码
                .concat("&response_type=code&scope=snsapi_base&state=").concat(returnUrl).concat("#wechat_redirect");
        System.out.println(url);
        //  return "redirect:"+url;

        return new ModelAndView(new RedirectView(url));

    }





}
