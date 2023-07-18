package com.example.librarymanagment.repository;

import com.example.librarymanagment.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findAdminByUsername(String username);
}
