package com.example.shop_service.Controller;

import com.example.shop_service.DTO.InventoryDTO;
import com.example.shop_service.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{eggId}")
    public ResponseEntity<InventoryDTO> getInventoryByEggId(@PathVariable Long eggId) {
        InventoryDTO inventoryDTO = inventoryService.getInventoryByEggId(eggId);
        return inventoryDTO != null ? ResponseEntity.ok(inventoryDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventories() {
        List<InventoryDTO> allInventories = inventoryService.getAllInventories();
        return ResponseEntity.ok(allInventories);
    }

    @PutMapping("/updateInventory/{eggId}")
    public ResponseEntity<InventoryDTO> updateInventory(@PathVariable Long eggId, @RequestParam int quantity) {
        InventoryDTO updatedInventory = inventoryService.updateInventory(eggId, quantity);
        return updatedInventory != null ? ResponseEntity.ok(updatedInventory) : ResponseEntity.notFound().build();
    }

    // Additional endpoints can be added based on your specific use cases
}
