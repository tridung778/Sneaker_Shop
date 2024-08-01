package com.example.Java6_ASM.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private UUID productId;
    private String thumbnail;
    private String name;
    private String category;
    private double price;
    private int quantity;
}
