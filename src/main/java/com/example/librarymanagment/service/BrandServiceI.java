package com.example.librarymanagment.service;

import com.example.librarymanagment.model.dto.request.BrandRequestDto;
import com.example.librarymanagment.model.dto.response.BrandResponseDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;

import java.util.List;

public interface BrandServiceI {
    ResponseDto saveBrand(BrandRequestDto brandRequestDto,Long categoryId);
    ResponseDto deleteBrand(Long id);
    ResponseDto updateBrand(BrandRequestDto brandRequestDto,Long id);
    List<BrandResponseDto> getAllBrands(Long categoryId);
    BrandResponseDto getBrandById(Long categoryId,Long id);
}
