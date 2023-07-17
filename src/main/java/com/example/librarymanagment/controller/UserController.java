package com.example.librarymanagment.controller;

import com.example.librarymanagment.model.dto.request.CartRequestDto;
import com.example.librarymanagment.model.dto.response.BookResponseDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.service.BookServiceI;
import com.example.librarymanagment.service.CartServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final BookServiceI bookServiceI;
    private final CartServiceI cartServiceI;

    @GetMapping("/{user_id}/books")
    public List<BookResponseDto> getBooks(){
        return bookServiceI.getAll();
    }
    @GetMapping("/{user_id}/books/{id}")
    public BookResponseDto getBookById(@PathVariable Long id){
        return bookServiceI.getById(id);
    }

    @PostMapping("/{user_id}/books/{id}/borrow/cart")
    public ResponseDto addBookToCart(@PathVariable Long id, CartRequestDto cartRequestDto){
        return cartServiceI.save(id,cartRequestDto);
    }

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello qaqa!");
    }

}
