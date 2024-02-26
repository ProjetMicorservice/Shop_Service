package com.example.shop_service.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter @Setter
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long playerId;
    @ManyToOne
    @JoinColumn(name = "egg_id")
    private EggEntity egg;
    private int quantity;
    private double totalPrice;
    private Date purchaseDate;
}
