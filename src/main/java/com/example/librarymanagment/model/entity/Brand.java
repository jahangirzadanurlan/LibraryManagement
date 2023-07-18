package com.example.librarymanagment.model.entity;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;

    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL)
    List<Book> books;
    @ManyToOne(cascade = CascadeType.ALL)
    Category category;

}
