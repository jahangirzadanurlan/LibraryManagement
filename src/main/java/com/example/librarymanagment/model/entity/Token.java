package com.example.librarymanagment.model.entity;

import com.example.librarymanagment.model.enums.TokenType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String token;
    @Enumerated(EnumType.STRING)
    TokenType tokenType=TokenType.BERAER;
    boolean revoked;
    boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    User user;


}
