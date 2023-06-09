package com.example.librarymanagment.service.impl;

import com.example.librarymanagment.dto.request.CartRequestDto;
import com.example.librarymanagment.dto.response.ResponseDto;
import com.example.librarymanagment.entity.Book;
import com.example.librarymanagment.entity.Cart;
import com.example.librarymanagment.repository.CartRepository;
import com.example.librarymanagment.service.BookService;
import com.example.librarymanagment.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final BookService bookService;
    private final ModelMapper modelMapper;
    @Override
    public ResponseDto save(Long bookId, CartRequestDto cartRequestDto) {
        Cart cart=modelMapper.map(cartRequestDto,Cart.class);
        Book book=modelMapper.map(bookService.getById(bookId),Book.class);

        if(book!=null){
            List<Book> books= cart.getBooks();
            if (books!=null){
                cart.getBooks().add(book);
            }else {
                List<Book> bookList=new ArrayList<>();
                bookList.add(book);
                cart.setBooks(bookList);
            }
            cartRepository.save(cart);
            return new ResponseDto("Save is successful!");
        }else {
            return new ResponseDto("Save is unsuccessful!");
        }
    }
}
