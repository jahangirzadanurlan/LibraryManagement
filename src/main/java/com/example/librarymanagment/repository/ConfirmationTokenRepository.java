package com.example.librarymanagment.repository;

import com.example.librarymanagment.model.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
    ConfirmationToken findConfirmationTokenByToken(String token);
}
