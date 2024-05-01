package com.example.exampleservice.util;


import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MailUtil {
    @Value("${smtp.email:@naver.com}")
    private String email;
    private final JavaMailSender javaMailSender;
    private MimeMessage createMessage(String code, String email){
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.addRecipients(Message.RecipientType.TO, email);
            message.setSubject("인증 번호입니다.");
            message.setText("이메일 인증코드: "+ code);
            message.setFrom(new InternetAddress(email +"@naver.com"));
            return message;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMail(String code, String email) {
        MimeMessage mimeMessage = createMessage(code, email);
        javaMailSender.send(mimeMessage);
    }

    public String sendCertificationMail(String code, String email)  {
        sendMail(code, email);
        return code;
    }
}
