package com.example.librarymanagment.service.impl;

import com.example.librarymanagment.model.dto.request.ClientRequestDto;
import com.example.librarymanagment.model.entity.ConfirmationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RequiredArgsConstructor
@Slf4j
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    public void sendMail(ClientRequestDto clientRequestDto, ConfirmationToken confirmationToken){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(clientRequestDto.getEmail());
        simpleMailMessage.setSubject("Confirm account!");
        simpleMailMessage.setText("user/confirm/"+confirmationToken.getToken());

        javaMailSender.send(simpleMailMessage);
        log.info("Message sent: "+clientRequestDto);
    }

}
