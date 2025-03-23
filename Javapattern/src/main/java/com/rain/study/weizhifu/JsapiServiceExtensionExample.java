package com.rain.study.weizhifu;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.exception.HttpException;
import com.wechat.pay.java.core.exception.MalformedMessageException;
import com.wechat.pay.java.core.exception.ServiceException;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.*;
import com.wechat.pay.java.service.payments.model.Transaction;

public class JsapiServiceExtensionExample {
  /** 商户号 */
  public static String merchantId = "1694598655";
  /** 商户API私钥路径 */
  public static String privateKeyPath = "E:/study/git/springbootdemo/Javapattern/src/main/resources/cert/apiclient_key.pem";
  /** 商户证书序列号 */
  public static String merchantSerialNumber = "44E8E082CAC6FD9BE74E6BE22AF1CB9CA57A8A4E";
  /** 商户APIV3密钥 */
  public static String apiV3Key = "26F0D7A3082C150EC5BB45EA519871F0";
  public static String appid = "wx43034e041f51ae56";
  public static JsapiServiceExtension service;

  public static void main(String[] args) {
    // 初始化商户配置
    Config config =
        new RSAAutoCertificateConfig.Builder()
            .merchantId(merchantId)
            // 使用 com.wechat.pay.java.core.util 中的函数从本地文件中加载商户私钥，商户私钥会用来生成请求的签名
            .privateKeyFromPath(privateKeyPath)
            .merchantSerialNumber(merchantSerialNumber)
            .apiV3Key(apiV3Key)
            .build();
    // 初始化服务
    service =
        new JsapiServiceExtension.Builder()
            .config(config)
            .signType("RSA") // 不填默认为RSA
            .build();
    try {
      System.out.println("=============");
      // ... 调用接口
      PrepayWithRequestPaymentResponse response = prepayWithRequestPayment();
      System.out.println("=============");
      System.out.println(response);
    } catch (HttpException e) {
      e.printStackTrace();
      // 发送HTTP请求失败
      // 调用e.getHttpRequest()获取请求打印日志或上报监控，更多方法见HttpException定义
    } catch (ServiceException e) { // 服务返回状态小于200或大于等于300，例如500
      e.printStackTrace();
      // 调用e.getResponseBody()获取返回体打印日志或上报监控，更多方法见ServiceException定义
    } catch (MalformedMessageException e) { // 服务返回成功，返回体类型不合法，或者解析返回体失败
      e.printStackTrace();
      // 调用e.getMessage()获取信息打印日志或上报监控，更多方法见MalformedMessageException定义
    }
  }

  /** 关闭订单 */
  public static void closeOrder() {

    CloseOrderRequest request = new CloseOrderRequest();
    // 调用request.setXxx(val)设置所需参数，具体参数可见Request定义
    // 调用接口
    service.closeOrder(request);
  }

  /** JSAPI支付下单，并返回JSAPI调起支付数据 */
  public static PrepayWithRequestPaymentResponse prepayWithRequestPayment() {
    PrepayRequest request = new PrepayRequest();

    Amount amount = new Amount();
    amount.setTotal(100);
    request.setAmount(amount);
    request.setAppid(appid);
    request.setMchid(merchantId);
    request.setDescription("测试商品标题");
    request.setNotifyUrl("https://notify_url");
    request.setOutTradeNo("out_trade_no_001");
    Payer payer = new Payer();
    payer.setOpenid("openid");
    request.setPayer(payer);
    // 调用request.setXxx(val)设置所需参数，具体参数可见Request定义
    // 调用接口
    return service.prepayWithRequestPayment(request);
  }

  /** 微信支付订单号查询订单 */
  public static Transaction queryOrderById() {

    QueryOrderByIdRequest request = new QueryOrderByIdRequest();
    // 调用request.setXxx(val)设置所需参数，具体参数可见Request定义
    // 调用接口
    return service.queryOrderById(request);
  }

  /** 商户订单号查询订单 */
  public static Transaction queryOrderByOutTradeNo() {

    QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
    // 调用request.setXxx(val)设置所需参数，具体参数可见Request定义
    // 调用接口
    return service.queryOrderByOutTradeNo(request);
  }
}
