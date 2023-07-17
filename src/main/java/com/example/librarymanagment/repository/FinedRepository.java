package com.example.librarymanagment.repository;

import com.example.librarymanagment.model.entity.Fined;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinedRepository extends JpaRepository<Fined,Long> {

}
