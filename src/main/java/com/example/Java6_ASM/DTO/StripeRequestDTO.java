package com.example.Java6_ASM.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StripeRequestDTO {

    private Long amount;

    private String email;

    private String productName;
}