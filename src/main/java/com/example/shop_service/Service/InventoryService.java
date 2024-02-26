package com.example.shop_service.Service;

import com.example.shop_service.DTO.InventoryDTO;

import java.util.List;

public interface InventoryService {
    InventoryDTO getInventoryByEggId(Long eggId);
    List<InventoryDTO> getAllInventories();
    InventoryDTO updateInventory(Long eggId, int quantity);
}
