package com.example.librarymanagment.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionEnums {
    USER_NOT_FOUND_EXCEPTİON(HttpStatus.NOT_FOUND,"User not found"),
    BOOK_NOT_FOUND_EXCEPTİON(HttpStatus.NOT_FOUND,"Book not found"),
    BRAND_NOT_FOUND_EXCEPTİON(HttpStatus.NOT_FOUND,"Brand not found"),
    CATEGORY_NOT_FOUND_EXCEPTİON(HttpStatus.NOT_FOUND,"Category not found"),
    REQUEST_NOT_FOUND_EXCEPTİON(HttpStatus.NOT_FOUND,"Request not found");


    private final HttpStatus httpStatus;
    private final String message;
}
