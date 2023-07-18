package com.example.librarymanagment.service;

import com.example.librarymanagment.model.dto.request.AdminRequestDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;

public interface AdminServiceI {
    ResponseDto saveAdmin(AdminRequestDto adminRequestDto);
}
