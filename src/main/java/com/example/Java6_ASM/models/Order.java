package com.example.Java6_ASM.models;

import com.example.Java6_ASM.enums.OrderStatus;
import com.example.Java6_ASM.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
