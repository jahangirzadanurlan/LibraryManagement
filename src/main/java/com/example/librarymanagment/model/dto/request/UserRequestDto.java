package com.example.librarymanagment.model.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
public class UserRequestDto {
    @NotNull
    @Size(max = 15,message = "Password must be at least 6 characters long")
    String username;
    @Email
    String email;
    String address;
    @Size(min = 6,message = "Password must be at least 6 characters long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d).+$",message = "Password must contain at least one letter and one digit")
    String password;
}
