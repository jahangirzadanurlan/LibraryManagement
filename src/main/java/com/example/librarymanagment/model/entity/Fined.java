package com.example.librarymanagment.model.entity;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
public class Fined {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String clientName;
    String clientEmail;
    LocalDate startDate;
    LocalDate endDate;

    @OneToOne
    User user;

}
