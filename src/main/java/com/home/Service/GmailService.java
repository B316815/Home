package com.home.Service;

import com.home.paylod.MailBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class GmailService {

    @Value("${spring.mail.username}")
      private String sender;
    private JavaMailSender javaMailSender;

    public GmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMessage(MailBody mailBody){
        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(mailBody.to());
        message.setFrom("sender");
        message.setSubject(mailBody.subject());
        message.setText(mailBody.text());

        javaMailSender.send(message);

    }
}
