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
public class Fined {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String clientName;
    String clientEmail;
    Date startDate;
    Date endDate;

    @OneToOne
    Client client;

}