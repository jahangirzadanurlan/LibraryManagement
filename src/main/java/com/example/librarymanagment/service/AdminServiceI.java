package com.example.librarymanagment.service;

import com.example.librarymanagment.model.dto.request.AdminRequestDto;
import com.example.librarymanagment.model.dto.response.AuthenticationResponse;
import com.example.librarymanagment.model.dto.response.ResponseDto;

public interface AdminServiceI {
    AuthenticationResponse registerAdmin(AdminRequestDto adminRequestDto);
}
