package com.starer.util;

import com.aliyuncs.dysmsapi.model.v20170525.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Author: pengxiong
 * @Date: 2024/01/01 14:51:09
 * @Version: V1.0
 * @Description:
 **/
public class SendMessageUtil {

    public static final String EMAIL_SENDER = "2482723192@qq.com";
    public static final String PHONE_SENDER = "";
    /**
     *
     * @param receiverEmail 接收验证码的邮箱
     * @return 验证码
     */
    public static Integer sendIdentifyCodeByEmail(String receiverEmail) {
        final String username = "2482723192@qq.com";
        final String password = "dswabrvjolbfechb";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
            message.setSubject("验证码");
            String code = RandomNumberGenerator.randomCode();
            message.setText(code);

            Transport.send(message);
            return Integer.parseInt(code);

        } catch (MessagingException e) {
            return null;
        }
    }

    private static final String PRODUCT = "Dysmsapi";
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";
    private static final String REGION_ID = "cn-hangzhou"; // 您的区域ID，例如："cn-hangzhou"

    // 短信相关的参数
    private static final String TEMPLATE_CODE = "SMS_XXXX"; // 您的短信模板CODE
    private static final String SIGN_NAME = "您的短信签名";

    /**
     *
     * @param receiverPhone 接收验证码的手机号
     * @return 验证码
     */
    public static Integer sendIdentifyCodeByPhone(String receiverPhone) {
        String code = RandomNumberGenerator.randomCode();
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, "yourAccessKeyId", "yourAccessKeySecret");
        IAcsClient client = new DefaultAcsClient(profile);

        // 创建API请求并设置参数
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(receiverPhone); // 设置接收号码
        request.setSignName(SIGN_NAME); // 设置签名名称
        request.setTemplateCode(TEMPLATE_CODE); // 设置模板CODE
        request.setTemplateParam("{\"code\":\"" + code + "\"}"); // 设置模板参数（根据模板要求填充）

        try {
            // 发起请求并处理响应
            SendSmsResponse response = client.getAcsResponse(request);
            return Integer.parseInt(code);
        } catch (ClientException e) {
            System.err.println("短信发送失败，原因：" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
