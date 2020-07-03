package com.vault.demo.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016102600762191";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCHXfnItp3HX7VwLWX3/btgfp5IsHBSk3rlV8qPTWrE11UlrFY5jMyvHBMGAmxbUxEt08ljV4Md/7GkmswIi2wFDK0TWSi/KUHEKTYsGHkW+vMKh7i/HhuEhjhBqRBy39sFuMTLA9x4eh0vUEa+oZvEOpUz9okwoMIY75/9GcuC0o6XLc9zaoFRRXLagO5nkyoFmTRXqd/rJejYmH7wLUqwRQauscCqFhHL8FP0Ej5wBrX/7lq6Tr6d0U8FLDszszt8Wyk9YkmX2OKL52b4CyO8CKiN1HROJwEpY5qXqJyqh0z2RaHTZcvxFx/0XG8vrfEVTD9wAIvHoiKhDiQjQgO7AgMBAAECggEAOmRRUUFlAhHJBGCw8a6j21YDFTv218gCZVffar1sZwi0FBIpB54y1P55PkhhUWudp3UKFC41wwT/LhAQH0d1CyCSsBvvtbYoRmO4mIDeCwmpOUueZ+Km4B29uqUhEUx7ABbZ3PEHc3jJihVIzwaLWLQklEuuliliULtc+iLcZMuqSZnZTeEYgzSZ4DOYjokLY0UMTbnH02fngpartjdZ4zh3vuqEzFQIxM5CNjg7Ioc2/J1dxpyUUmbyZgMG9/xdK6mAMCfhmHsh8GQaOg7M7KHVZQgkGp/uE9IikUgKbKQMKv6Saj7PU6EvtIqRV7hivz0CnW589FsSK4mcGSBUcQKBgQC/M/lqMxZlV6D4zVhWBOVy6F8layD7X97OBPmhraxb7DYWGI2bmwqeUSteDD9XLA2wK9xN+Ib0BogpWEOVZfXPfr1o845Cj9d6SoJ1tDH/kh6pyKCceAXd9BnGDhqOJwBJ5n8DWNycV4ZPyjdbBc/0Rcs/V8bgqvABCB0Osb3ubQKBgQC1PeUGTCIjJPjh9yiNWsknfZLg/L5ObcJXo5/k1x0MlvDCC+7mmqfLRkGONyDSs9wakS7hDh2J8ZN3vwHG5ssoXRwdCc4SOHt0NoufQbOpGqhcRRtrSjP3eOCCxWgP9Ny5bkEumWLUn7yl4LO0WnYrr3e18aOBih9on6BwPuRBxwKBgC3WKOGzs5fB5R4gJCAi9lqQqFaREwwQFJqxCKMgvmQfAWxUoIYAKhCABvR53SlprzJfoWWzwa54bmXy+eiz07TVflTNpnoAf7iaaiILe7z364/qP86i2001OoSHVwhjAye0MRi9Uc3NEa7JHHIet5aosKvTwCKV+AlXR5gBNjYVAoGAMcjIqkwrbF/xhZBwsTNo4dIakHach8qagZxOhXuZXSXxCDxi6TE+OhYYV8pI19fT8/BBYkyQTJ3xFgl/oXUwBOxelpNk6ZheQPPismJHyYGrEX1iJLyDPc0UTpePGCL4pm7ktLopbaJbHW11r2rR3voZ9f5kHWclbYW7elmsNW0CgYBhBnPzqLdxWRkqIM4c1Ykr3PBNf+zq9OjohUlwQylE/nza+wlQ1Q/j6sjYqPYkEwDu+b/LrvW6W2tbzLR3j5IvFtugMUgx5fbwLXcpv+EdtfUSlq0Jjrxwr9lq64Voy/GHst6UKHDL3FJMJK+XUpHY6BrC4Hr0vkfw1G0xfpfCbw==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgdbxJ4+HE63izQUh91LxSPhh6YPXmzr47Nr8jduhUn2XgUwxyqqltxY9oUGxn+6+q12XyJD2fjIfafAYjtTrw3kHSqEI2UXqg88KSq/a4kw8SptJ5PMTmHDD0KTDIjkvI+RNQ7i/+F9wymQuIRlGCbeEvh+DS0A4hP734jThDYgxl24P93z8HQN95hvZPiE63ysfl1sUP7nOm4/K0MPQbe0DWLxg3qMULj+Qriv6Mhna198CyswjHJFhC59UNLTQB9oT66hWWvkXNbI+w95pa1iEEW3ctNlqOm51Y5hHJreMa5G9XTJDBzbyVgZOHcLSeB1VICBRR7v+P7fVdxmFaQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/user/notify_url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/user/return_url";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = " https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";

    // 返回格式
    public static String FORMAT = "json";


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

