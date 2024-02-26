package com.example.shop_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class EggDTO {
    private Long id;
    private String type;
    private double price;
    private int quantity;

    // Getters and setters
}
