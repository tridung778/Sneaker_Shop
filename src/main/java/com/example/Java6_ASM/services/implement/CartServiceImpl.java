package com.example.Java6_ASM.services.implement;

import com.example.Java6_ASM.models.Cart;
import com.example.Java6_ASM.repositories.CartRepository;
import com.example.Java6_ASM.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addItemToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllItemInCart(UUID id) {
        return cartRepository.findAllByAccountId(id);
    }
}
