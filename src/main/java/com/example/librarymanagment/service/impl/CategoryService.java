package com.example.librarymanagment.service.impl;

import com.example.librarymanagment.exception.CategoryNotFoundException;
import com.example.librarymanagment.model.dto.request.CategoryRequestDto;
import com.example.librarymanagment.model.dto.response.CategoryResponseDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.model.entity.Category;
import com.example.librarymanagment.repository.CategoryRepository;
import com.example.librarymanagment.service.CategoryServiceI;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceI {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseDto saveCategory(CategoryRequestDto categoryRequestDto) {
        Category save = categoryRepository.save(modelMapper.map(categoryRequestDto, Category.class));
        return save!=null ? new ResponseDto("Category created successfull"):
                new ResponseDto("Category created unsuccessfully!");
    }

    @Override
    public ResponseDto deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        categoryRepository.delete(category.orElseThrow());
        return new ResponseDto("Category deleted");
    }

    @Override
    public ResponseDto updateCategory(CategoryRequestDto categoryRequestDto,Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        category.orElseThrow().setDescription(categoryRequestDto.getDescription());
        category.orElseThrow().setName(categoryRequestDto.getName());

        Category save = categoryRepository.save(category.orElseThrow());
        return save!=null ? new ResponseDto("Category updated successfull"):
                new ResponseDto("Category updated unsuccessfully!");
    }

    @Override
    public List<CategoryResponseDto> getAllCategory() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        return modelMapper.map(category.orElseThrow(()->new CategoryNotFoundException()),CategoryResponseDto.class);
    }
}
