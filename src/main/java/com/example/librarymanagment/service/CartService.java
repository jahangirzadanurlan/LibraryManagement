package com.example.librarymanagment.service;


import com.example.librarymanagment.dto.request.CartRequestDto;
import com.example.librarymanagment.dto.response.ResponseDto;

public interface CartService {

     ResponseDto save(Long id,CartRequestDto cartRequestDto);


}
