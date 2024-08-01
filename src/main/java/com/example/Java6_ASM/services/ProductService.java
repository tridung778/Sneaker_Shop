package com.example.Java6_ASM.services;

import com.example.Java6_ASM.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void saveAllProduct(List<Product> products);
    List<Product> finAll();

    Product finById(UUID id);

    List<Product> findByCategoryId(String cid);

    Product create(Product product);

    Product update(Product product);

    void delete(UUID id);
}
