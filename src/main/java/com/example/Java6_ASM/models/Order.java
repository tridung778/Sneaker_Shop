package com.example.Java6_ASM.models;

import com.example.Java6_ASM.enums.OrderStatus;
import com.example.Java6_ASM.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseModel {

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    Date createdAt = new Date();

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
