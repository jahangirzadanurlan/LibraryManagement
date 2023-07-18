package com.example.librarymanagment.service.impl;

import com.example.librarymanagment.model.dto.request.AuthenticationRequest;
import com.example.librarymanagment.model.dto.request.UserRequestDto;
import com.example.librarymanagment.model.dto.response.AuthenticationResponse;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.model.entity.ConfirmationToken;
import com.example.librarymanagment.model.entity.Token;
import com.example.librarymanagment.model.entity.User;
import com.example.librarymanagment.model.enums.Role;
import com.example.librarymanagment.repository.TokenRepository;
import com.example.librarymanagment.repository.UserRepository;
import com.example.librarymanagment.service.impl.ConfirmationTokenService;
import com.example.librarymanagment.service.impl.EmailSenderService;
import com.example.librarymanagment.service.impl.security.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(UserRequestDto request){
        User _user = User.builder()
                .username(request.getUsername())
                .address(request.getAddress())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(_user);

        User user=userRepository.findUserByEmailOrName(request.getUsername())
                .orElseThrow(null);
        ConfirmationToken confirmationToken=ConfirmationToken.builder()
                .user(user)
                .createdAt(LocalDateTime.now())
                .token(UUID.randomUUID().toString())
                .build();
        confirmationTokenService.save(confirmationToken);
        emailSenderService.sendMail(request.getEmail(),confirmationToken);

        var accessToken=jwtService.generateToken(user);
        var refreshToken=jwtService.generateRefreshToken(user);
        saveUserToken(user,accessToken);

        return AuthenticationResponse
                .builder()
                .message("Account successfully created. Please activate your account by clicking on the link sent to your email.")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public ResponseDto confirm(UUID uuid){
        ConfirmationToken token = confirmationTokenService.getTokenByUUID(uuid.toString());
        if (token!=null){
            token.setConfirmedAt(LocalDateTime.now());
            User user = token.getUser();
            user.setEnabled(true);
            confirmationTokenService.save(token);
            userRepository.save(user);
            return new ResponseDto(user.getUsername()+" Confirm is successfull!");
        }else {
            return new ResponseDto("Confirm link is wrong!");
        }
    }

    //login
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findUserByEmailOrName(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserToken(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
        final String username;
        final String refreshToken;

        if (authHeader==null || !authHeader.startsWith("Bearer")){
            return;
        }
        refreshToken=authHeader.substring(7);
        username= jwtService.extractUsername(refreshToken);
        if (username!=null){
            var user = userRepository.findUserByEmailOrName(username)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken,user)){
                var accessToken=jwtService.generateToken(user);
                revokeAllUserToken(user);
                saveUserToken(user,accessToken);
                var authResponse=AuthenticationResponse
                        .builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public void saveUserToken(User user, String jwtToken){
        var token=Token.builder()
                .user(user)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public void revokeAllUserToken(User user){
        var validUserTokens=tokenRepository.findAllValidTokenByUser(user.getId());
        if(validUserTokens.isEmpty()){
            return;
        }
        validUserTokens.forEach(
                token -> {
                    token.setExpired(true);
                    token.setRevoked(true);
                }
        );
        tokenRepository.saveAll(validUserTokens);
    }


}
