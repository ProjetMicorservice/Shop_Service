package com.example.shop_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InventoryDTO {
    private Long eggId;
    private int quantity;
    private Date lastRenewalDate;

    // Getters and setters
}