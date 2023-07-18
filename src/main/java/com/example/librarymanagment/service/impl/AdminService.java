package com.example.librarymanagment.service.impl;

import com.example.librarymanagment.model.dto.request.AdminRequestDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.model.entity.Admin;
import com.example.librarymanagment.model.enums.Role;
import com.example.librarymanagment.repository.AdminRepository;
import com.example.librarymanagment.service.AdminServiceI;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminServiceI {
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseDto saveAdmin(AdminRequestDto request) {
        if (request.getSystemPassword().equals("admin123")){
            Admin admin=Admin.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ADMIN)
                    .build();

            Admin save= adminRepository.save(admin);
            return admin != null ? new ResponseDto("Admin Created is successfull"):
                    new ResponseDto("Admin Created is unsuccessfull!");
        }else {
            return new ResponseDto("System password is wrong!");
        }
    }
}
