package com.example.librarymanagment.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String author;
    Integer requestStatus;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    User user;

}
