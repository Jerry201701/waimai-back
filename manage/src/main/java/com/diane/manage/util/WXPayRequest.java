package com.diane.manage.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import static com.diane.manage.constants.WXPayConstants.USER_AGENT;

/**
 * 微信支付请求类
 *
 * @author yclimb
 * @date 2018/8/17
 */
public class WXPayRequest {
    //商户端小程序appid
    public static final String APPID= "xxxxxxxxxxxxxxx";
    //微信支付的商户id
    public static final String MCH_ID= "xxxxxxxxxxxxxx";
    //微信支付的商户密钥  开发者问领导，去微信商户平台去申请（要下插件等等）
    public static final String KEY = "xxxxxxxxxxxxxx";
    //支付成功后的服务器回调url，这里填PayController里的回调函数地址
    public static final String NOTIFY_URL = "https://xxxxxxxxxxxxxxxx/notify";
    //签名方式，固定值
    public static final String WITHDRAWAL_URL="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";


    private WXPayConfig config;

    public WXPayRequest(WXPayConfig config) throws Exception {

        this.config = config;
    }

    /**
     * 请求，只请求一次，不做重试
     * @return
     * @throws Exception
     */
    public String requestOnce(String data, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        BasicHttpClientConnectionManager connManager;


            char[] password = config.getMchID().toCharArray();
            InputStream certStream = config.getCertStream();
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(certStream, password);

            // 实例化密钥库 & 初始化密钥工厂
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, password);

            // 创建 SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    new String[]{"TLSv1"},
                    null,
                    new DefaultHostnameVerifier());

            connManager = new BasicHttpClientConnectionManager(
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", sslConnectionSocketFactory)
                            .build(),
                    null,
                    null,
                    null
            );


        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();

        HttpPost httpPost = new HttpPost(WITHDRAWAL_URL);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeoutMs).setConnectTimeout(connectTimeoutMs).build();
        httpPost.setConfig(requestConfig);

        StringEntity postEntity = new StringEntity(data, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("User-Agent", USER_AGENT + " " + config.getMchID());
        httpPost.setEntity(postEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity, "UTF-8");

    }



}
