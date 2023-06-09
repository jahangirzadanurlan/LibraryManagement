package com.example.librarymanagment.controller;

import com.example.librarymanagment.dto.request.CartRequestDto;
import com.example.librarymanagment.dto.response.BookResponseDto;
import com.example.librarymanagment.dto.response.ResponseDto;
import com.example.librarymanagment.service.BookService;
import com.example.librarymanagment.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final BookService bookService;
    private final CartService cartService;

    @GetMapping("/{user_id}/books")
    public List<BookResponseDto> getBooks(){
        return bookService.getAll();
    }
    @GetMapping("/{user_id}/books/{id}")
    public BookResponseDto getBookById(@PathVariable Long id){
        return bookService.getById(id);
    }

    @PostMapping("/{user_id}/books/{id}/borrow/cart")
    public ResponseDto addBookToCart(@PathVariable Long id, CartRequestDto cartRequestDto){
        return cartService.save(id,cartRequestDto);
    }
}
