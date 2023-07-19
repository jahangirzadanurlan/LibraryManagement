package com.example.librarymanagment.service.impl;

import com.example.librarymanagment.model.dto.request.BookRequestDto;
import com.example.librarymanagment.model.dto.response.BookResponseDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.model.entity.*;
import com.example.librarymanagment.repository.*;
import com.example.librarymanagment.service.BookServiceI;
import com.example.librarymanagment.service.impl.security.JwtService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService implements BookServiceI {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final BorrowDateRepository borrowDateRepository;
    private final BookStatusRepository bookStatusRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;


    @Override
    public ResponseDto saveBook(BookRequestDto request, Long categoryId, Long brandId) {
        Optional<Brand> brandById = brandRepository.findById(brandId);
        Book book = Book.builder()
                .name(request.getName().toLowerCase())
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

    @Override
    public BookResponseDto getBookByName(String bookName) {
        Book book = bookRepository.findBookByName(bookName.toLowerCase());

        return modelMapper.map(book, BookResponseDto.class);
    }

    @Override
    public ResponseDto borrowBook(
            @NonNull HttpServletRequest request,
            Long categoryId, Long brandId, Long id) {

        if (!checkUser(request)){
            return new ResponseDto("Please confirm your account!");
        }

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

        BorrowDate borrowDate=BorrowDate.builder()
                .book(book)
                .start_date(LocalDateTime.now())
                .end_date(LocalDate.now().plusDays(15))
                .build();
        borrowDateRepository.save(borrowDate);

        BookStatus bookStatus=BookStatus.builder()
                .book(book)
                .borrow_status(1)
                .buy_status(0)
                .build();
        bookStatusRepository.save(bookStatus);

        book.setBorrowDate(borrowDate);
        book.setBookStatus(bookStatus);

        Book save = bookRepository.save(book);
        return save != null ? new ResponseDto("Borrow successfully"):
                new ResponseDto("Borrow unsuccessfully!");
    }

    private boolean checkUser(HttpServletRequest request) {
        final String authHeader=request.getHeader("Authorization");
        log.info("Header '{}' accepted .", authHeader);

        String jwtToken=authHeader.substring(7);
        String username=jwtService.extractUsername(jwtToken);

        if (username!=null){
            Optional<User> user = userRepository.findUserByEmailOrName(username);

            if (user.orElseThrow().isEnabled()){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }


}
