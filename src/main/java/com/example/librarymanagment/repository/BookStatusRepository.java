package com.example.librarymanagment.repository;

import com.example.librarymanagment.model.entity.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStatusRepository extends JpaRepository<BookStatus,Long> {

}
