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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    List<Brand> brands;

}
