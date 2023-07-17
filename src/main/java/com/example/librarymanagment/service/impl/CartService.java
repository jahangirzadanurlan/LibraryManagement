package com.example.librarymanagment.service.impl;

import com.example.librarymanagment.model.dto.request.CartRequestDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.model.entity.Book;
import com.example.librarymanagment.model.entity.Cart;
import com.example.librarymanagment.repository.CartRepository;
import com.example.librarymanagment.service.BookServiceI;
import com.example.librarymanagment.service.CartServiceI;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService implements CartServiceI {
    private final CartRepository cartRepository;
    private final BookServiceI bookServiceI;
    private final ModelMapper modelMapper;
    @Override
    public ResponseDto save(Long bookId, CartRequestDto cartRequestDto) {
        Cart cart=modelMapper.map(cartRequestDto,Cart.class);
        Book book=modelMapper.map(bookServiceI.getById(bookId),Book.class);

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
