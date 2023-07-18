package com.example.librarymanagment.service;

import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.model.entity.ConfirmationToken;

public interface ConfirmationTokenServiceI {
    ResponseDto save(ConfirmationToken confirmationToken);
    ConfirmationToken getTokenByUUID(String uuid);
}
