package com.example.shop_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TransactionDTO {
    private Long id;
    private Long playerId;
    private String transactionType;
    private EggDTO egg;
    private int quantity;
    private double totalPrice;
    private Date transactionDate;

    // Getters and setters
}
