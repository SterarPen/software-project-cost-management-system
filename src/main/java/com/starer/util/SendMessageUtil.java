package com.starer.util;

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

    /**
     *
     * @param receiverEmail 接收验证码的邮箱
     * @return 验证码
     */
    public static Integer sendIdentifyCodeByEmail(String receiverEmail) {
        final String username = "2482723192@qq.com";
        final String password = "ppvngdwfcfygecdg";

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

    /**
     *
     * @param receiverPhone 接收验证码的手机号
     * @return 验证码
     */
    public static Integer sendIdentifyCodeByPhone(String receiverPhone) {
        return 111111;
    }
}
