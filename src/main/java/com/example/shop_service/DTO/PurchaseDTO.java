package com.example.shop_service.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@Setter @Getter
public class PurchaseDTO {
    private Long id;
    private Long playerId;
    private EggDTO egg;
    private int quantity;
    private double totalPrice;
    private Date purchaseDate;

    // Getters and setters
}