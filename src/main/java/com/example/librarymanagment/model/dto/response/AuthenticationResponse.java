package com.example.librarymanagment.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
public class AuthenticationResponse {
    String message;
    String accessToken;
    String refreshToken;
}
