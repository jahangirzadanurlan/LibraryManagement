package com.example.librarymanagment.service.impl;

import com.example.librarymanagment.model.dto.request.AdminRequestDto;
import com.example.librarymanagment.model.dto.request.UserRequestDto;
import com.example.librarymanagment.model.dto.response.AuthenticationResponse;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.model.entity.Admin;
import com.example.librarymanagment.model.entity.ConfirmationToken;
import com.example.librarymanagment.model.entity.User;
import com.example.librarymanagment.model.enums.Role;
import com.example.librarymanagment.repository.AdminRepository;
import com.example.librarymanagment.service.AdminServiceI;
import com.example.librarymanagment.service.impl.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminServiceI {
    private final AdminRepository adminRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse registerAdmin(AdminRequestDto request){
        if (request.getSystemPassword().equals("admin123")){
            Admin _admin = Admin.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ADMIN)
                    .build();
            adminRepository.save(_admin);

            Admin admin=adminRepository.findAdminByUsername(request.getUsername());

            var accessToken=jwtService.generateToken(admin);
            var refreshToken=jwtService.generateRefreshToken(admin);

            return AuthenticationResponse
                    .builder()
                    .message("Account successfully created.")
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }else {
            throw new RuntimeException("System password is wrong!");
        }
    }
}
