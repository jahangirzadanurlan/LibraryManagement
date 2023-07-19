package com.example.librarymanagment.service;

import com.example.librarymanagment.model.dto.request.RequestRequestDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.model.entity.Request;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RequestServiceI {
    ResponseDto saveRequest(HttpServletRequest httpRequest,RequestRequestDto request);
    ResponseDto updateRequestStatus(Long id);
    List<Request> getAllRequests();
}
