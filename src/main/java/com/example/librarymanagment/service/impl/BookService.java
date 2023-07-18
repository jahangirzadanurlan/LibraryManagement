package com.example.librarymanagment.service.impl;

import com.example.librarymanagment.model.dto.request.BookRequestDto;
import com.example.librarymanagment.model.dto.request.BrandRequestDto;
import com.example.librarymanagment.model.dto.response.BookResponseDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.model.entity.Book;
import com.example.librarymanagment.model.entity.Brand;
import com.example.librarymanagment.model.entity.Category;
import com.example.librarymanagment.repository.BookRepository;
import com.example.librarymanagment.repository.BrandRepository;
import com.example.librarymanagment.repository.CategoryRepository;
import com.example.librarymanagment.service.BookServiceI;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService implements BookServiceI {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;


    @Override
    public ResponseDto saveBook(BookRequestDto request, Long categoryId, Long brandId) {
        Optional<Brand> brandById = brandRepository.findById(brandId);
        Book book = Book.builder()
                .name(request.getName())
                .author(request.getAuthor())
                .amount(request.getAmount())
                .remainCount(request.getRemainCount())
                .brand(brandById.orElseThrow())
                .build();
        bookRepository.save(book);

        return book != null ? new ResponseDto("Book save successfully"):
                new ResponseDto("Book save unsuccessfully!");
    }

    @Override
    public ResponseDto updateBook(BookRequestDto bookRequestDto, Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book!=null){
            book.orElseThrow().setAuthor(bookRequestDto.getAuthor());
            book.orElseThrow().setName(bookRequestDto.getName());
            book.orElseThrow().setAmount(bookRequestDto.getAmount());
            book.orElseThrow().setRemainCount(bookRequestDto.getRemainCount());
            Book save = bookRepository.save(book.orElseThrow());

            return save != null ? new ResponseDto("Book updating is successfull"):
                    new ResponseDto("Book updating is unsuccessfull!");
        }else {
            return new ResponseDto("Book not found!");
        }
    }

    @Override
    public List<BookResponseDto> getAllBooks(Long categoryId, Long brandId) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        List<Brand> brands = category.orElseThrow().getBrands();
        Brand filteredBrand = brands.stream()
                .filter(brand -> brand.getId() == brandId)
                .findFirst()
                .orElseThrow();

        List<BookResponseDto> collect = filteredBrand.getBooks().stream()
                .map(book -> modelMapper.map(book, BookResponseDto.class))
                .collect(Collectors.toList());

        return collect;
    }

    @Override
    public BookResponseDto getBookById(Long categoryId, Long brandId, Long id) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        List<Brand> brands = category.orElseThrow().getBrands();
        Brand filteredBrand = brands.stream()
                .filter(brand -> brand.getId() == brandId)
                .findFirst()
                .orElseThrow();

        Book book = filteredBrand.getBooks().stream()
                .filter(_book -> _book.getId()==id)
                .findFirst()
                .orElseThrow();

        return modelMapper.map(book, BookResponseDto.class);
    }
}
