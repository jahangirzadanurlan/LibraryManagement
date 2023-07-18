package com.example.librarymanagment.model.entity;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
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

    @OneToOne(cascade = CascadeType.ALL)
    BookStatus bookStatus;
    @ManyToOne
    Brand brand;
    @OneToOne(cascade = CascadeType.ALL)
    BorrowDate borrowDate;

}
