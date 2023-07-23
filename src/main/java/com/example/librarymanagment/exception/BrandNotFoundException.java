package com.example.librarymanagment.exception;

import com.example.librarymanagment.model.enums.ExceptionEnums;

public class BrandNotFoundException extends RuntimeException{
    public BrandNotFoundException(){
        super(ExceptionEnums.BRAND_NOT_FOUND_EXCEPTİON.getMessage());
    }
}
