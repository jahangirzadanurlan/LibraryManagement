package com.example.librarymanagment.service;

import com.example.librarymanagment.model.dto.request.BookRequestDto;
import com.example.librarymanagment.model.dto.response.BookResponseDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;

import java.util.List;

public interface BookServiceI {
    ResponseDto saveBook(BookRequestDto bookRequestDto,Long categoryId,Long brandId);
    ResponseDto updateBook(BookRequestDto bookRequestDto,Long id);
    List<BookResponseDto> getAllBooks(Long categoryId,Long brandId);
    BookResponseDto getBookById(Long categoryId,Long brandId,Long id);

}
