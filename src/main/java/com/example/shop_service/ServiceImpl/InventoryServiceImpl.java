package com.example.shop_service.ServiceImpl;

import com.example.shop_service.DTO.InventoryDTO;
import com.example.shop_service.Model.InventoryEntity;
import com.example.shop_service.Repository.InventoryRepository;
import com.example.shop_service.Service.EggService;
import com.example.shop_service.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final EggService eggService;  // Assuming you have an EggService for retrieving egg details

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository, EggService eggService) {
        this.inventoryRepository = inventoryRepository;
        this.eggService = eggService;
    }

    @Override
    public InventoryDTO getInventoryByEggId(Long eggId) {
        Optional<InventoryEntity> inventoryOptional = inventoryRepository.findById(eggId);
        return inventoryOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<InventoryDTO> getAllInventories() {
        List<InventoryEntity> allInventories = inventoryRepository.findAll();
        return allInventories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public InventoryDTO updateInventory(Long eggId, int quantity) {
        Optional<InventoryEntity> existingInventoryOptional = inventoryRepository.findById(eggId);

        if (existingInventoryOptional.isPresent()) {
            InventoryEntity existingInventory = existingInventoryOptional.get();
            // Update fields based on your business logic
            existingInventory.setQuantity(existingInventory.getQuantity() + quantity);

            InventoryEntity updatedInventory = inventoryRepository.save(existingInventory);
            return convertToDTO(updatedInventory);
        } else {
            // Handle inventory not found
            return null;
        }
    }

    // Additional methods can be added based on your specific use cases

    // Helper methods for conversion between DTO and Entity
    private InventoryDTO convertToDTO(InventoryEntity inventoryEntity) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setEggId(inventoryEntity.getEggId());
        inventoryDTO.setQuantity(inventoryEntity.getQuantity());
        inventoryDTO.setLastRenewalDate(inventoryEntity.getLastRenewalDate());
        return inventoryDTO;
    }

    private InventoryEntity convertToEntity(InventoryDTO inventoryDTO) {
        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setEggId(inventoryDTO.getEggId());
        inventoryEntity.setQuantity(inventoryDTO.getQuantity());
        inventoryEntity.setLastRenewalDate(inventoryDTO.getLastRenewalDate());
        return inventoryEntity;
    }
}
