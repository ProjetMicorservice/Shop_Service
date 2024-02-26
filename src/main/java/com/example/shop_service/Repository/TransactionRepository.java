package com.example.shop_service.Repository;

import com.example.shop_service.Model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByPlayerId(Long playerId);
    // Custom queries can be added here if needed
}
