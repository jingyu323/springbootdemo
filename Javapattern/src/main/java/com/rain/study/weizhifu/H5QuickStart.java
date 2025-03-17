package com.rain.study.weizhifu;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.h5.H5Service;
import com.wechat.pay.java.service.payments.h5.model.Amount;
import com.wechat.pay.java.service.payments.h5.model.PrepayRequest;
import com.wechat.pay.java.service.payments.h5.model.PrepayResponse;
import com.wechat.pay.java.service.payments.h5.model.SceneInfo;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;

/** Native 支付下单为例 */
public class H5QuickStart {

    /** 商户号 */
    public static String merchantId = "1694598655";
    /** 商户API私钥路径 */
    public static String privateKeyPath = "E:/study/git/springbootdemo/Javapattern/src/main/resources/cert/apiclient_key.pem";
    /** 商户证书序列号 */
    public static String merchantSerialNumber = "44E8E082CAC6FD9BE74E6BE22AF1CB9CA57A8A4E";
    /** 商户APIV3密钥 */
    public static String apiV3Key = "26F0D7A3082C150EC5BB45EA519871F0";
    public static String appid = "wx43034e041f51ae56";
    public static H5Service service;
    public static void main(String[] args) {
        // 使用自动更新平台证书的RSA配置
        // 一个商户号只能初始化一个配置，否则会因为重复的下载任务报错
        Config config =
                new RSAAutoCertificateConfig.Builder()
                        .merchantId(merchantId)
                        .privateKeyFromPath(privateKeyPath)
                        .merchantSerialNumber(merchantSerialNumber)
                        .apiV3Key(apiV3Key)
                        .build();
        // 构建service

        // 初始化服务
        service = new H5Service.Builder().config(config).build();


        H5QuickStart.prepay();

    }

    /** H5支付下单 */
    public static PrepayResponse prepay() {
        PrepayRequest request = new PrepayRequest();
        // 调用request.setXxx(val)设置所需参数，具体参数可见Request定义
        // 调用接口
        Amount amount = new Amount();
        amount.setTotal(100);
        request.setAmount(amount);

        SceneInfo sceneInfo = new SceneInfo();
        sceneInfo.setPayerClientIp("1.196");
        request.setSceneInfo(sceneInfo);
        request.setAppid(appid);
        request.setMchid(merchantId);
        request.setDescription("测试商品标题");
        request.setNotifyUrl("https://notify_url");
        request.setOutTradeNo("out_trade_no_001");
        return service.prepay(request);
    }
}
