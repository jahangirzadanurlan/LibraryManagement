package com.example.librarymanagment.controller;

import com.example.librarymanagment.model.dto.request.AdminRequestDto;
import com.example.librarymanagment.model.dto.request.BookRequestDto;
import com.example.librarymanagment.model.dto.request.BrandRequestDto;
import com.example.librarymanagment.model.dto.request.CategoryRequestDto;
import com.example.librarymanagment.model.dto.response.*;
import com.example.librarymanagment.service.AdminServiceI;
import com.example.librarymanagment.service.BookServiceI;
import com.example.librarymanagment.service.BrandServiceI;
import com.example.librarymanagment.service.CategoryServiceI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    public final AdminServiceI adminService;
    public final CategoryServiceI categoryService;
    private final BrandServiceI brandService;
    private final BookServiceI bookService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> saveAdmin(@RequestBody AdminRequestDto request){
        log.info("POST:: /register request body -> {}",request);
        AuthenticationResponse response = adminService.registerAdmin(request);
        log.info("POST:: /register response body -> {}",response);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/category")
    public ResponseEntity<ResponseDto> saveCategory(@RequestBody CategoryRequestDto request){
        log.info("POST:: /category request body -> {}",request);
        ResponseDto response = categoryService.saveCategory(request);
        log.info("POST:: /category response body -> {}",response);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<ResponseDto> updateCategory(@RequestBody CategoryRequestDto request,@PathVariable Long id){
        log.info("PUT:: /category/{} request body -> {}",id,request);
        ResponseDto response = categoryService.updateCategory(request,id);
        log.info("PUT:: /category/{} response body -> {}",id,response);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<ResponseDto> deleteCategory(@PathVariable Long id){
        log.info("DELETE:: /category/{} request",id);
        ResponseDto response = categoryService.deleteCategory(id);
        log.info("DELETE:: /category/{} response body -> {}",id,response);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategory(){
        log.info("GET:: /category request");
        List<CategoryResponseDto> response = categoryService.getAllCategory();
        log.info("GET:: /category response body -> {}",response);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long id){
        log.info("GET:: /category/{} request",id);
        CategoryResponseDto response = categoryService.getCategoryById(id);
        log.info("GET:: /category/{} response body -> {}",id,response);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/category/{catId}/brand")
    public ResponseEntity<ResponseDto> saveBrand(@RequestBody BrandRequestDto request,@PathVariable Long catId){
        log.info("POST:: /category/{}/brand request body -> {}",catId,request);
        ResponseDto response = brandService.saveBrand(request, catId);
        log.info("POST:: /{}/category request body -> {}",catId,request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/category/{catId}/brand/{id}")
    public ResponseEntity<ResponseDto> updateBrand(@RequestBody BrandRequestDto request,@PathVariable Long catId,@PathVariable Long id){
        log.info("PUT:: /category/{}/brand/{} request body -> {}",catId,id,request);
        ResponseDto response = brandService.updateBrand(request,id);
        log.info("PUT:: /category/{}/brand/{} response body -> {}",catId,id,response);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/category/{catId}/brand/{id}")
    public ResponseEntity<ResponseDto> deleteBrand(@PathVariable Long catId,@PathVariable Long id){
        log.info("DELETE:: /category/{}/brand/{} request",catId,id);
        ResponseDto response = categoryService.deleteCategory(id);
        log.info("DELETE:: /category/{}/brand/{} response body -> {}",catId,id,response);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{catId}/brand")
    public ResponseEntity<List<BrandResponseDto>> getAllBrands(@PathVariable Long catId){
        log.info("GET:: /category/{}/brand request",catId);
        List<BrandResponseDto> response = brandService.getAllBrands(catId);
        log.info("GET:: /category/{}/brand response body -> {}",catId,response);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{catId}/brand/{id}")
    public ResponseEntity<BrandResponseDto> getBrandById(@PathVariable Long catId,@PathVariable Long id){
        log.info("GET:: /category/{}/brand/{} request",catId,id);
        BrandResponseDto response = brandService.getBrandById(catId,id);
        log.info("GET:: /category/{}/brand/{} response body -> {}",catId,id,response);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/category/{catId}/brand/{brandId}/book")
    public ResponseEntity<ResponseDto> saveBook(@RequestBody BookRequestDto request,@PathVariable Long catId,@PathVariable Long brandId){
        log.info("POST:: /category/{}/brand/{}/book request body -> {}",catId,brandId,request);
        ResponseDto response = bookService.saveBook(request, catId, brandId);
        log.info("POST:: /category/{}/brand/{}/book response body -> {}",catId,brandId,request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{catId}/brand/{brandId}/book")
    public ResponseEntity<List<BookResponseDto>> getAllBrandBooks(@PathVariable Long catId, @PathVariable Long brandId){
        log.info("GET:: /category/{}/brand/{}/book request",catId,brandId);
        List<BookResponseDto> response = bookService.getAllBooks(catId,brandId);
        log.info("GET:: /category/{}/brand/{}/book response body -> {}",catId,brandId,response);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{catId}/brand/{brandId}/book/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long catId, @PathVariable Long brandId,@PathVariable Long id){
        log.info("GET:: /category/{}/brand/{}/book/{} request",catId,brandId,id);
        BookResponseDto response = bookService.getBookById(catId,brandId,id);
        log.info("GET:: /category/{}/brand/{}/book/{} response body -> {}",catId,brandId,id,response);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<ResponseDto> updateBook(@RequestBody BookRequestDto request,@PathVariable Long id){
        log.info("PUT:: /book/{} request body -> {}",id,request);
        ResponseDto response = bookService.updateBook(request,id);
        log.info("UT:: /book/{} response body -> {}",id,response);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/demo")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello admin!");
    }

}
