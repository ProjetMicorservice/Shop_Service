package com.example.shop_service.Repository;

import com.example.shop_service.Model.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    // Custom queries can be added here if needed
}
