package com.example.librarymanagment.controller;

import com.example.librarymanagment.model.dto.request.RequestRequestDto;
import com.example.librarymanagment.model.dto.response.BookResponseDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.model.entity.Request;
import com.example.librarymanagment.service.RequestServiceI;
import com.example.librarymanagment.service.impl.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/moderator")
@RequiredArgsConstructor
@Slf4j
public class ModeratorController {
    private final RequestServiceI requestService;
    @GetMapping("/request")
    public ResponseEntity<List<Request>> getAllRequests(){
        log.info("GET:: /request request");
        List<Request> response = requestService.getAllRequests();
        log.info("GET:: /request response body -> {}",response);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/request/{id}")
    public ResponseEntity<ResponseDto> acceptRequest(@PathVariable Long id){
        log.info("POST:: /request/{id}r request id -> {}",id);
        ResponseDto response = requestService.updateRequestStatus(id);
        log.info("POST:: /request/{} request body -> {}",id,response);
        return ResponseEntity.ok(response);
    }
}
