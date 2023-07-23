package com.example.librarymanagment.exception;

import com.example.librarymanagment.model.enums.ExceptionEnums;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super(ExceptionEnums.USER_NOT_FOUND_EXCEPTİON.getMessage());
    }
}
