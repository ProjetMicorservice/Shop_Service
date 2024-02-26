package com.example.shop_service.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class InventoryEntity {
    @Id
    private Long eggId;
    private int quantity;
    private Date lastRenewalDate;
}
