package com.daroguzo.simplelms.component;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Component
public class MailComponents {

    private final JavaMailSender javaMailSender;

    public void sendMailTest() {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("kjw2122@naver.com");
        message.setSubject("안녕하세요 테스트입니다.");
        message.setText("안녕하세요 내용입니다.");

        javaMailSender.send(message);
    }

    public boolean sendMail(String email, String subject, String text) {

        boolean result = false;

        MimeMessagePreparator message = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(email);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text, true);
            }
        };
        try {
            javaMailSender.send(message);
            result = true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
