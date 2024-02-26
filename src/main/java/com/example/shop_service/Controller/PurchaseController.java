package com.example.shop_service.Controller;

import com.example.shop_service.DTO.PurchaseDTO;
import com.example.shop_service.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<PurchaseDTO> getPurchaseById(@PathVariable Long purchaseId) {
        PurchaseDTO purchaseDTO = purchaseService.getPurchaseById(purchaseId);
        return purchaseDTO != null ? ResponseEntity.ok(purchaseDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDTO>> getAllPurchases() {
        List<PurchaseDTO> allPurchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(allPurchases);
    }

    @PostMapping("/makePurchase")
    public ResponseEntity<PurchaseDTO> makePurchase(@RequestParam Long playerId, @RequestParam Long eggId, @RequestParam int quantity) {
        PurchaseDTO purchaseDTO = purchaseService.makePurchase(playerId, eggId, quantity);
        return purchaseDTO != null ? ResponseEntity.ok(purchaseDTO) : ResponseEntity.badRequest().build();
    }

    // Additional endpoints can be added based on your specific use cases
}
