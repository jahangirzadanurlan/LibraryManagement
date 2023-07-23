package com.example.librarymanagment.exception;

import com.example.librarymanagment.model.enums.ExceptionEnums;

public class RequestNotFoundException extends RuntimeException{
    public RequestNotFoundException(){
        super(ExceptionEnums.REQUEST_NOT_FOUND_EXCEPTİON.getMessage());
    }
}
