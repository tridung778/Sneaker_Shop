package com.example.Java6_ASM.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StripeResponseDTO {

    private String intentId;
    private String clientSecret;

}
