package com.example.librarymanagment.controller;

import com.example.librarymanagment.model.dto.request.*;
import com.example.librarymanagment.model.dto.response.AuthenticationResponse;
import com.example.librarymanagment.model.dto.response.BookResponseDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.service.BookServiceI;
import com.example.librarymanagment.service.RequestServiceI;
import com.example.librarymanagment.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final BookServiceI bookService;
    private final RequestServiceI requestService;

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
        AuthenticationResponse response= userService.registerUser(request);
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

    @GetMapping("/category/{catId}/brand/{brandId}/book")
    public ResponseEntity<List<BookResponseDto>> getAllBrandBooks(@PathVariable Long catId, @PathVariable Long brandId){
        log.info("GET:: /category/{}/brand/{}/book request",catId,brandId);
        List<BookResponseDto> response = bookService.getAllBooks(catId,brandId);
        log.info("GET:: /category/{}/brand/{}/book response body -> {}",catId,brandId,response);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{catId}/brand/{brandId}/book/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long catId, @PathVariable Long brandId,@PathVariable Long id){
        log.info("GET:: /category/{}/brand/{}/book/{} request",catId,brandId,id);
        BookResponseDto response = bookService.getBookById(catId,brandId,id);
        log.info("GET:: /category/{}/brand/{}/book/{} response body -> {}",catId,brandId,id,response);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/book/{name}")
    public ResponseEntity<BookResponseDto> getBookByName(@PathVariable String name){
        log.info("GET:: /book/{} request",name);
        BookResponseDto response = bookService.getBookByName(name);
        log.info("GET:: /book/{} response body -> {}",name,response);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/category/{catId}/brand/{brandId}/book/{id}")
    public ResponseEntity<ResponseDto> borrowBook(
            HttpServletRequest request,
            @PathVariable Long catId,
            @PathVariable Long brandId,
            @PathVariable Long id){

        log.info("GET:: /category/{}/brand/{}/book/{} request",catId,brandId,id);
        ResponseDto response = bookService.borrowBook(request,catId,brandId,id);
        log.info("GET:: /category/{}/brand/{}/book/{} response body -> {}",catId,brandId,id,response);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/request")
    public ResponseEntity<ResponseDto> requestBook(HttpServletRequest httpRequest,@RequestBody RequestRequestDto request){
        log.info("POST:: /register request body -> {}",request);
        ResponseDto response = requestService.saveRequest(httpRequest, request);
        log.info("POST:: /register request body -> {}",response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/demo")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello user!");
    }




}
