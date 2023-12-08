package com.dev.mygavel.configEmail;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service

public class MailServiceImpl implements  MailService {

@Autowired

    private JavaMailSender mailSender;


    @Override
    public void sendTextEmail(String sendTo, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        try {

            msg.setTo(sendTo);
            msg.setSubject(subject);
            msg.setText(body);
            mailSender.send(msg);
        }



        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
