package com.example.librarymanagment.service;

import com.example.librarymanagment.model.dto.request.BookRequestDto;
import com.example.librarymanagment.model.dto.response.BookResponseDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;

import java.util.List;

public interface BookServiceI {
    ResponseDto save(BookRequestDto bookRequestDto);
    List<BookResponseDto> getAll();
    BookResponseDto getById(Long id);

}
