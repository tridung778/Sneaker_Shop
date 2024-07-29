package com.example.Java6_ASM.services;

import com.example.Java6_ASM.models.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    void saveAllProduct(List<Product> products);
    
    List<Product> findAll();

    Optional<Product> findById(UUID id);

	List<Product> findByCategoryId(UUID cid);
}
