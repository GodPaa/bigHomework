package com.group2.dingmall.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000118673422";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDB4tsgTSjM9nJHGIjNqY72fhB8U27Dj5doGJs8djD8moajuKPokiQJetOYrzW4fKL9a71B7XlGey4uG3wkq/W1QfLtqgl0WyGtTHbYxg7AvSYYzxSmQ05vn3w53wttnLRyaB8fOnBD9vjAqo5obYJz+XHge/Qy+XCQdekP4k1dPlR9Qt9YKnPamh1Cp56G7+YOxEhyf3KZgY0SGN1ZBNq2sNzyRB+/N1XGrE1qvYhAf2VLIPWPqh2Ag4USN/G3dykRTUrJDW6gLIR9uVoab9PI8Y5x1Yux74tWsSDZzoRhSuisE0l73PTz58wsA1ZEKu12MwotQ1qcrbduJsDnBqPxAgMBAAECggEAKMyzLd3uywrFS363MwgORIKMt4zbDx4FT2t2J0f70Pw3fvZYz5UJLlGBe2zLUjIwHkHwxpHRZmp/ckZoXlMtVmI1W5vfd3PB53TiwYUUD6z9+lSBVOdu9wBJpOfxwLQsKLMOcYTboQG0oN6DLyh0PjFsrD+JSLzC2jNlwujtPMSGGMWZhKgbTFBLDTqJ4zBRTds5NWVnYWGQA0VK+RXP2QEe6atpxi7mxQ9KXvS56I+i3JgSVjwHQ75aA/qa6MsbcARVwBL16BEdrh4VhGNl4JW4L9Po/1ftyPfFrYlLV0wjEilgAJVhTDY3znYnmBxiYzp9NrXMzos5He8L/ybxwQKBgQDsG9wjFhOelqGLxtgM2wBroAZYzN0GIbKL9eeNSaWQZU6Tv8wBUp8JV7dhYIDQkvZO+ZGMOMUc+eVmBV509i5gM0BOJCSyCGsj3bYPYciupCDRmn3B5gvMlv0cqvXc9fxzDvs73Luf7utnz+Ohcrv6SFs8w9563pdOnr2cpuOYGQKBgQDSOGISG0NBOBktKRC47+PiyiKxp3q40ier8murdNc1Gp/JP1iERHMUuiKUHzFcC+FYeOVlUv1UIxdETLZ2GiTvfoyXlJ3HxGFISOuhjKkbmlNZmBj5kozn4tBQCyaG/1rQwGkms2TKqOLZZ4psScbRLd0j6SloW8v6kAi/AVJFmQKBgF7FqA0lVWa4y8tRW3N9jKOmriWCxc0qRYLMh888moplP68qs3ZsdY3IecFKfSo7c0asAnIob2Z8IEpdKVcFYgQXTl6PP3N+0ZKp8eQmuejv1ISN4KCt+Fp82MhjDyFMU7LTdqDGr0E5I6Cd3570cOO7m3O+o1TqeA2ExlNU4dIxAoGBALHs8OycUI8p2P1FLZcpyzqPlU/CDffSP6ukEgdbbC7RKv6dc+D3QmP/qvatdAyRLZjBMn9Q/4Mv9ChluWU6eZqPBJXjPqvvBdu1hGIGeLcZJoTFXBYYONSnP2MQHPrTtFzNDfKokZ2J1f7mSN2gj/tKE0qqtNZUI9AeCpkHmEe5AoGBAJi26SqeeOm3onKi7cNIjZRybKeC+NgJxVHhjJb5gWHJCjHZ+VA3FqueeQqvkYbignV4kOfTWmpqoV01y1CkXKrM01vdAYn3L6Nz13gttWmeUEr567lmwi+79hym5QCh7FEEMyUJqpnLVsuJWacBBst/IWEeVq0UUqTyfaawGm3e";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlKWBg8XFZpEwxB0HYoEFGyBMhW/ZEOGfZYy4RPEeumlt7kwro8kS32yDiYVwANXG/OPVV3qtar8iJTIdFA+p4CqEBKS2liUbTX+uVuEGqpRDTNMIsL4qMMBWk9WjULIva2cFbYayx/8M+QhT2Zzy1gpUfygZdbO9prkXEddPOsCcNgJDg8gny/HPGj9zp7lnj06+6QbUDSnKzKWNzWgIFCqMCKckl2k5vGraAU43iKBd5HbgaoOrcEydnmBdLH3LD2lkbPnzSyss/vQSmWNthkpyBhB/xWxEWBfFcMmNxKCdDiKfN735H6qrR2D2zbfH69cQUfwODjEoJ0w3bDpleQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://付款成功页面/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // http://付款成功页面/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp
    public static String return_url = "http://www.baidu.com";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志路径
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

