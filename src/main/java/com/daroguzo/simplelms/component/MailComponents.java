package com.daroguzo.simplelms.component;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

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
}
