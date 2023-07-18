package com.example.librarymanagment.service.impl;

import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.model.entity.ConfirmationToken;
import com.example.librarymanagment.repository.ConfirmationTokenRepository;
import com.example.librarymanagment.service.ConfirmationTokenServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService implements ConfirmationTokenServiceI {
    private final ConfirmationTokenRepository confirmTokenRepository;
    @Override
    public ResponseDto save(ConfirmationToken confirmationToken) {
        ConfirmationToken save = confirmTokenRepository.save(confirmationToken);
        return save != null ? new ResponseDto("Save is successfull") :
                new ResponseDto("Save is unseccessfull");
    }

    @Override
    public ConfirmationToken getTokenByUUID(String uuid) {
        return confirmTokenRepository.findConfirmationTokenByToken(uuid);
    }
}
