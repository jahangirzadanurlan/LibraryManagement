package com.example.librarymanagment.service.impl;

import com.example.librarymanagment.model.entity.ConfirmationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    public void sendMail(String email, ConfirmationToken confirmationToken){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Confirm account!");
        simpleMailMessage.setText("user/confirm/"+confirmationToken.getToken());

        javaMailSender.send(simpleMailMessage);
        log.info("Message sent: "+email);
    }

}
