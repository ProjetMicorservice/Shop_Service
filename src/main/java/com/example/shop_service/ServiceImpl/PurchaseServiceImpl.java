package com.example.shop_service.ServiceImpl;

import com.example.shop_service.DTO.EggDTO;
import com.example.shop_service.DTO.PurchaseDTO;
import com.example.shop_service.Model.EggEntity;
import com.example.shop_service.Model.PurchaseEntity;
import com.example.shop_service.Repository.PurchaseRepository;
import com.example.shop_service.Service.EggService;
import com.example.shop_service.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final EggService eggService;  // Assuming you have an EggService for retrieving egg details

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, EggService eggService) {
        this.purchaseRepository = purchaseRepository;
        this.eggService = eggService;
    }

    @Override
    public PurchaseDTO getPurchaseById(Long purchaseId) {
        Optional<PurchaseEntity> purchaseOptional = purchaseRepository.findById(purchaseId);
        return purchaseOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<PurchaseDTO> getAllPurchases() {
        List<PurchaseEntity> allPurchases = purchaseRepository.findAll();
        return allPurchases.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public PurchaseDTO makePurchase(Long playerId, Long eggId, int quantity) {
        // Retrieve egg details to calculate total price
        EggDTO eggDTO = eggService.getEggById(eggId);

        if (eggDTO != null && eggDTO.getQuantity() >= quantity) {
            double totalPrice = eggDTO.getPrice() * quantity;

            // Deduct the purchased eggs from the inventory
            eggService.updateEgg(eggId, new EggDTO(eggDTO.getId(), eggDTO.getType(), eggDTO.getPrice(), eggDTO.getQuantity() - quantity));

            // Save the purchase transaction
            PurchaseDTO purchaseDTO = new PurchaseDTO();
            purchaseDTO.setPlayerId(playerId);
            purchaseDTO.setEgg(eggDTO);
            purchaseDTO.setQuantity(quantity);
            purchaseDTO.setTotalPrice(totalPrice);
            purchaseDTO.setPurchaseDate(new Date());  // Assuming you have a proper date handling mechanism

            PurchaseEntity purchaseEntity = convertToEntity(purchaseDTO);
            PurchaseEntity savedEntity = purchaseRepository.save(purchaseEntity);

            return convertToDTO(savedEntity);
        } else {
            // Handle insufficient stock or non-existent egg
            return null;
        }
    }

    // Additional methods can be added based on your specific use cases

    // Helper methods for conversion between DTO and Entity
    private PurchaseDTO convertToDTO(PurchaseEntity purchaseEntity) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setId(purchaseEntity.getId());
        purchaseDTO.setPlayerId(purchaseEntity.getPlayerId());
        purchaseDTO.setEgg(convertToDTO(purchaseEntity.getEgg()));
        purchaseDTO.setQuantity(purchaseEntity.getQuantity());
        purchaseDTO.setTotalPrice(purchaseEntity.getTotalPrice());
        purchaseDTO.setPurchaseDate(purchaseEntity.getPurchaseDate());
        return purchaseDTO;
    }

    private PurchaseEntity convertToEntity(PurchaseDTO purchaseDTO) {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(purchaseDTO.getId());
        purchaseEntity.setPlayerId(purchaseDTO.getPlayerId());
        purchaseEntity.setEgg(convertToEntity(purchaseDTO.getEgg()));
        purchaseEntity.setQuantity(purchaseDTO.getQuantity());
        purchaseEntity.setTotalPrice(purchaseDTO.getTotalPrice());
        purchaseEntity.setPurchaseDate(purchaseDTO.getPurchaseDate());
        return purchaseEntity;
    }

    // Helper method for converting EggDTO to EggEntity and vice versa
    private EggDTO convertToDTO(EggEntity eggEntity) {
        EggDTO eggDTO = new EggDTO();
        eggDTO.setId(eggEntity.getId());
        eggDTO.setType(eggEntity.getType());
        eggDTO.setPrice(eggEntity.getPrice());
        eggDTO.setQuantity(eggEntity.getQuantity());
        return eggDTO;
    }

    private EggEntity convertToEntity(EggDTO eggDTO) {
        EggEntity eggEntity = new EggEntity();
        eggEntity.setId(eggDTO.getId());
        eggEntity.setType(eggDTO.getType());
        eggEntity.setPrice(eggDTO.getPrice());
        eggEntity.setQuantity(eggDTO.getQuantity());
        return eggEntity;
    }
}
