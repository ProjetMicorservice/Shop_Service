package com.example.shop_service.Service;

import com.example.shop_service.DTO.PurchaseDTO;

import java.util.List;

public interface PurchaseService {
    PurchaseDTO getPurchaseById(Long purchaseId);
    List<PurchaseDTO> getAllPurchases();
    PurchaseDTO makePurchase(Long playerId, Long eggId, int quantity);
}
