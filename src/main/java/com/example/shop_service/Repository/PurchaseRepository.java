package com.example.shop_service.Repository;

import com.example.shop_service.Model.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
    // Custom queries can be added here if needed
}