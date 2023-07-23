package com.example.librarymanagment.exception;

import com.example.librarymanagment.model.enums.ExceptionEnums;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(){
        super(ExceptionEnums.CATEGORY_NOT_FOUND_EXCEPTİON.getMessage());
    }
}
