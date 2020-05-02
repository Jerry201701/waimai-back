package com.diane.manage.util;


import com.github.wxpay.sdk.WXPayUtil;
import java.util.HashMap;
import java.util.Map;

public class WithdrawalUtil {
    //连接超时时间，默认10秒
    private static final int socketTimeout = 10000;
    //传输超时时间，默认30秒
    private static final int connectTimeout = 30000;

    //微信支付的商户密钥  开发者问领导，去微信商户平台去申请（要下插件等等）
    public static final String KEY = "xxxxxxxxxxxxxxxxx";


    public static Integer withdrawRequest(String appid,
                                          String mchId,
                                          String orderNumber,
                                          String openid,
                                          String amount,
                                          String desc) {

        Map<String, String> parm = new HashMap<>();
        parm.put("mch_appid", appid); //商户端appid
        parm.put("mchid", mchId); //商户号
        parm.put("nonce_str", WXPayUtil.generateNonceStr()); //随机字符串
        parm.put("partner_trade_no", orderNumber); //商户订单号
        parm.put("openid", openid);
        parm.put("check_name", "NO_CHECK"); //校验用户姓名选项 OPTION_CHECK
        parm.put("amount",amount); //转账金额
        parm.put("desc", desc); //企业付款描述信息
        parm.put("spbill_create_ip", "120.25.121.42"); //Ip地址

        /*
        parm.put("mch_appid", APPID); //商户端appid
       // parm.put("mch_appid", DELIVERY_APPID); //配送端appid
        parm.put("mchid", MCH_ID); //商户号
        parm.put("nonce_str", WXPayUtil.generateNonceStr()); //随机字符串
        parm.put("partner_trade_no", "112"); //商户订单号
        parm.put("openid", "oJ8OL5ev0o-IiGakDLP7zFlkdnH4"); //商户端用户openid oCVr20N2YLH9VQztnkZTaCj2aYYY
       // parm.put("openid", "ou3_s4kM5rRrpIq8Limxem9ys57A"); //配送用户openid oCVr20N2YLH9VQztnkZTaCj2aYYY
        parm.put("check_name", "NO_CHECK"); //校验用户姓名选项 OPTION_CHECK
        parm.put("amount","1"); //转账金额
        parm.put("desc", "转账测试"); //企业付款描述信息
        parm.put("spbill_create_ip", "120.25.121.42"); //Ip地址


         */
        try {
            String xmlParam = WXPayUtil.generateSignedXml(parm, KEY);
            System.out.println(xmlParam);
            WXPayRequest wxPayRequest=new WXPayRequest(WXPayConfigImpl.getInstance());
            String result= wxPayRequest.requestOnce(xmlParam,connectTimeout,socketTimeout);
            Map<String,String>resultMap= WXPayUtil.xmlToMap(result);
            if (resultMap.get("result_code").equals("SUCCESS")){
                System.out.println("提现成功");
                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}
