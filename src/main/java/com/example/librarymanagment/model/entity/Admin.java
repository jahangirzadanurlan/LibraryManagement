package com.example.librarymanagment.model.entity;

import com.example.librarymanagment.model.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;

    @Enumerated(EnumType.STRING)
    Role role;

}
