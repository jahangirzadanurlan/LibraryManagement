package com.example.librarymanagment.controller;

import com.example.librarymanagment.model.dto.request.AuthenticationRequest;
import com.example.librarymanagment.model.dto.request.RegisterRequest;
import com.example.librarymanagment.model.dto.response.AuthenticationResponse;
import com.example.librarymanagment.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        log.info("POST:: /register request body -> {}",request);
        AuthenticationResponse response=authService.register(request);
        log.info("POST:: /register request body -> {}",request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public void refresh(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authService.refreshToken(request,response);
    }


}
