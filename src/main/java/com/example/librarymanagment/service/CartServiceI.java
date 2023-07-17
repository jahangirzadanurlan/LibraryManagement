package com.example.librarymanagment.service;


import com.example.librarymanagment.model.dto.request.CartRequestDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;

public interface CartServiceI {

     ResponseDto save(Long id,CartRequestDto cartRequestDto);


}
