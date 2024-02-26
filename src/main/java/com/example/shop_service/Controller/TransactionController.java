package com.example.shop_service.Controller;

import com.example.shop_service.DTO.TransactionDTO;
import com.example.shop_service.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long transactionId) {
        TransactionDTO transactionDTO = transactionService.getTransactionById(transactionId);
        return transactionDTO != null ? ResponseEntity.ok(transactionDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> allTransactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(allTransactions);
    }

    @GetMapping("/playerTransactions/{playerId}")
    public ResponseEntity<List<TransactionDTO>> getPlayerTransactions(@PathVariable Long playerId) {
        List<TransactionDTO> playerTransactions = transactionService.getPlayerTransactions(playerId);
        return ResponseEntity.ok(playerTransactions);
    }

    // Additional endpoints can be added based on your specific use cases
}
