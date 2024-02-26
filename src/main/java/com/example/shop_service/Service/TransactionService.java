package com.example.shop_service.Service;

import com.example.shop_service.DTO.TransactionDTO;

import java.util.List;

public interface TransactionService {
    TransactionDTO getTransactionById(Long transactionId);
    List<TransactionDTO> getAllTransactions();
    List<TransactionDTO> getPlayerTransactions(Long playerId);
}
