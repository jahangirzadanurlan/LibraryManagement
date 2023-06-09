package com.example.librarymanagment.service;

import com.example.librarymanagment.dto.request.BookRequestDto;
import com.example.librarymanagment.dto.response.BookResponseDto;
import com.example.librarymanagment.dto.response.ResponseDto;
import com.example.librarymanagment.entity.Book;

import java.util.List;

public interface BookService {
    ResponseDto save(BookRequestDto bookRequestDto);
    List<BookResponseDto> getAll();
    BookResponseDto getById(Long id);

}
