package com.example.librarymanagment.service;

import com.example.librarymanagment.model.dto.request.CategoryRequestDto;
import com.example.librarymanagment.model.dto.response.CategoryResponseDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;

import java.util.List;

public interface CategoryServiceI {
    ResponseDto saveCategory(CategoryRequestDto categoryRequestDto);
    ResponseDto deleteCategory(Long id);
    ResponseDto updateCategory(CategoryRequestDto categoryRequestDto,Long id);
    List<CategoryResponseDto> getAllCategory();
    CategoryResponseDto getCategoryById(Long id);

}
