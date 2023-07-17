package com.example.librarymanagment.model.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String author;
    Double amount;
    Integer remainCount;

    @OneToOne
    BookStatus bookStatus;
    @ManyToOne
    Brand brand;
    @OneToOne
    BorrowDate borrowDate;

}
