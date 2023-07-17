package com.example.librarymanagment.service.impl;

import com.example.librarymanagment.model.dto.request.BookRequestDto;
import com.example.librarymanagment.model.dto.response.BookResponseDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.model.entity.Book;
import com.example.librarymanagment.repository.BookRepository;
import com.example.librarymanagment.service.BookServiceI;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService implements BookServiceI {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseDto save(BookRequestDto bookRequestDto) {
        Book save = bookRepository.save(modelMapper.map(bookRequestDto, Book.class));
        return save!=null ? new ResponseDto("Save is successfull"):
                new ResponseDto("Save is unsuccessfull");
    }

    @Override
    public List<BookResponseDto> getAll() {
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book,BookResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDto getById(Long id) {
        return modelMapper.map(bookRepository.findBookById(id),BookResponseDto.class);
    }
}
