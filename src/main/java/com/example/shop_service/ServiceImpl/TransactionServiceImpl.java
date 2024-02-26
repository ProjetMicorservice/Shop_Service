package com.example.shop_service.ServiceImpl;

import com.example.shop_service.DTO.EggDTO;
import com.example.shop_service.DTO.TransactionDTO;
import com.example.shop_service.Model.EggEntity;
import com.example.shop_service.Model.TransactionEntity;
import com.example.shop_service.Repository.TransactionRepository;
import com.example.shop_service.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDTO getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> getPlayerTransactions(Long playerId) {
        return transactionRepository.findByPlayerId(playerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Additional methods can be added based on your specific use cases

    // Helper methods for conversion between DTO and Entity
    private TransactionDTO convertToDTO(TransactionEntity transactionEntity) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transactionEntity.getId());
        transactionDTO.setPlayerId(transactionEntity.getPlayerId());
        transactionDTO.setEgg(convertToDTO(transactionEntity.getEgg()));
        transactionDTO.setQuantity(transactionEntity.getQuantity());
        transactionDTO.setTotalPrice(transactionEntity.getTotalPrice());
        transactionDTO.setTransactionDate(transactionEntity.getTransactionDate());
        return transactionDTO;
    }

    private TransactionEntity convertToEntity(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(transactionDTO.getId());
        transactionEntity.setPlayerId(transactionDTO.getPlayerId());
        transactionEntity.setEgg(convertToEntity(transactionDTO.getEgg()));
        transactionEntity.setQuantity(transactionDTO.getQuantity());
        transactionEntity.setTotalPrice(transactionDTO.getTotalPrice());
        transactionEntity.setTransactionDate(transactionDTO.getTransactionDate());
        return transactionEntity;
    }

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
