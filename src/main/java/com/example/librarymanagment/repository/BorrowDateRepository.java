package com.example.librarymanagment.repository;

import com.example.librarymanagment.model.entity.BorrowDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowDateRepository extends JpaRepository<BorrowDate,Long> {

}
