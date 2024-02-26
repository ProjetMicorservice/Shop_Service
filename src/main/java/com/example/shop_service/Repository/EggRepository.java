package com.example.shop_service.Repository;

import com.example.shop_service.Model.EggEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EggRepository extends JpaRepository<EggEntity,Long> {
}
