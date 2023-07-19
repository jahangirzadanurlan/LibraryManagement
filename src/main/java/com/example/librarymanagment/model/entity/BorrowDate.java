package com.example.librarymanagment.model.entity;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
public class BorrowDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDateTime start_date;
    LocalDate end_date;

    @OneToOne
    Book book;
}
