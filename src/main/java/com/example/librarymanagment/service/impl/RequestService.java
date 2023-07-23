package com.example.librarymanagment.service.impl;

import com.example.librarymanagment.exception.RequestNotFoundException;
import com.example.librarymanagment.exception.UserNotFoundException;
import com.example.librarymanagment.model.dto.request.RequestRequestDto;
import com.example.librarymanagment.model.dto.response.ResponseDto;
import com.example.librarymanagment.model.entity.Request;
import com.example.librarymanagment.model.entity.User;
import com.example.librarymanagment.repository.RequestRepository;
import com.example.librarymanagment.repository.UserRepository;
import com.example.librarymanagment.service.RequestServiceI;
import com.example.librarymanagment.service.impl.security.JwtService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestService implements RequestServiceI {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public ResponseDto saveRequest(
            @NonNull HttpServletRequest httpRequest,
            RequestRequestDto request) {
        User user=findUser(httpRequest);

        if (user!=null){
            Request _request=Request.builder()
                    .name(request.getName())
                    .author(request.getAuthor())
                    .user(user)
                    .build();
            requestRepository.save(_request);
            return new ResponseDto("Request is successfully");
        }else {
            return new ResponseDto("Request is unsuccessfully");
        }
    }

    @Override
    public ResponseDto updateRequestStatus(Long id) {
        Optional<Request> request = requestRepository.findById(id);
        request.orElseThrow(()->new RequestNotFoundException()).setRequestStatus(1);
        requestRepository.save(request.orElseThrow());
        return new ResponseDto("Status changing,request accepting");
    }

    @Override
    public List<Request> getAllRequests() {
        List<Request> requests= requestRepository.findAll();
        return requests;
    }

    private User findUser(HttpServletRequest request) {
        final String authHeader=request.getHeader("Authorization");
        log.info("Header '{}' accepted .", authHeader);

        String jwtToken=authHeader.substring(7);
        String username=jwtService.extractUsername(jwtToken);

        if (username!=null){
            Optional<User> user = userRepository.findUserByEmailOrName(username);

            if (user.orElseThrow().isEnabled()){
                return user.orElseThrow(()->new UserNotFoundException());
            }else{
                return null;
            }
        }else {
            return null;
        }
    }
}
