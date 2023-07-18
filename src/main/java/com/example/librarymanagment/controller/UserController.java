package com.example.librarymanagment.controller;

import com.example.librarymanagment.model.dto.request.AuthenticationRequest;
import com.example.librarymanagment.model.dto.request.CartRequestDto;
import com.example.librarymanagment.model.dto.request.UserRequestDto;
import com.example.librarymanagment.model.dto.response.AuthenticationResponse;
import com.example.librarymanagment.model.dto.response.BookResponseDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.service.BookServiceI;
import com.example.librarymanagment.service.CartServiceI;
import com.example.librarymanagment.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final BookServiceI bookServiceI;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserRequestDto request){
        log.info("POST:: /register request body -> {}",request);
        AuthenticationResponse response= userService.register(request);
        log.info("POST:: /register response body -> {}",response);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/confirm/{uuid}")
    public ResponseEntity<ResponseDto> confirmUser(@PathVariable UUID uuid){
        log.info("GET:: /confirm request body -> {}",uuid);
        ResponseDto confirm = userService.confirm(uuid);
        log.info("GET:: /confirm response body -> {}",confirm);
        return ResponseEntity.ok(confirm);
    }

    @PostMapping("/refresh-token")
    public void refresh(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        userService.refreshToken(request,response);
    }

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello qaqa!");
    }


}
