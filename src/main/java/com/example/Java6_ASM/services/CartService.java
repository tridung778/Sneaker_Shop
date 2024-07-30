package com.example.Java6_ASM.services;

import com.example.Java6_ASM.models.Cart;

import java.util.List;
import java.util.UUID;

public interface CartService {

    List<Cart> getAllItemInCart(UUID id);

    Cart addItemToCart(Cart cart);
}
