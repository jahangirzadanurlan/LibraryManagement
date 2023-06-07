package com.example.librarymanagment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
public class BorrowDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date start_date;
    Date end_date;

    @OneToOne
    Book book;
}
