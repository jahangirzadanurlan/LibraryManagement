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
public class BookStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer borrow_status;
    Integer buy_status;

    @OneToOne
    Book book;

}
