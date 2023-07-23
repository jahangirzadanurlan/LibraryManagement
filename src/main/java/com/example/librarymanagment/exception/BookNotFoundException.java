package com.example.librarymanagment.exception;

import com.example.librarymanagment.model.enums.ExceptionEnums;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(){
        super(ExceptionEnums.BOOK_NOT_FOUND_EXCEPTİON.getMessage());
    }
}
