package com.example.librarymanagment.service.impl;

import com.example.librarymanagment.model.dto.request.BrandRequestDto;
import com.example.librarymanagment.model.dto.response.BrandResponseDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.model.entity.Brand;
import com.example.librarymanagment.model.entity.Category;
import com.example.librarymanagment.repository.BrandRepository;
import com.example.librarymanagment.repository.CategoryRepository;
import com.example.librarymanagment.service.BrandServiceI;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService implements BrandServiceI {
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseDto saveBrand(BrandRequestDto brandRequestDto,Long categoryId) {
        Brand existingBrand = brandRepository.findBrandByName(brandRequestDto.getName());
        if (existingBrand != null) {
            return new ResponseDto("Brand with the same name already exists!");
        }

        Category category=categoryRepository.findById(categoryId).orElseThrow();
        if (category==null){
            return new ResponseDto("You don't have category.Please,first of all create category.");
        }

        Brand newBrand=modelMapper.map(brandRequestDto, Brand.class);
        newBrand.setCategory(category);

        Brand saveBrand=brandRepository.save(newBrand);
        return saveBrand != null ? new ResponseDto("Brand creation is successful") :
                new ResponseDto("Brand creation is unsuccessful!");
    }

    @Override
    public ResponseDto deleteBrand(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        brandRepository.delete(brand.orElseThrow());
        return new ResponseDto("Brand deleting is successfull");
    }

    @Override
    public ResponseDto updateBrand(BrandRequestDto brandRequestDto, Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand!=null){
            brand.orElseThrow().setDescription(brandRequestDto.getDescription());
            brand.orElseThrow().setName(brandRequestDto.getName());
            Brand save = brandRepository.save(brand.orElseThrow());

            return save != null ? new ResponseDto("Brand updating is successfull"):
                    new ResponseDto("Brand updating is unsuccessfull!");
        }else {
            return new ResponseDto("Brand not found!");
        }
    }

    @Override//orElseThrow meselesi
    public List<BrandResponseDto> getAllBrands(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        return category.orElseThrow().getBrands().stream()
                .map(brand-> modelMapper.map(brand, BrandResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BrandResponseDto getBrandById(Long categoryId,Long id) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        Brand brand = category.orElseThrow().getBrands().get(id.intValue() - 1);

        return modelMapper.map(brand,BrandResponseDto.class);
    }
}
