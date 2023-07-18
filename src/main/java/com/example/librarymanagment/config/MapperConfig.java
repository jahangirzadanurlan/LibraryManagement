package com.example.librarymanagment.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MapperConfig {
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
