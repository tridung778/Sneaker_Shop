package com.example.Java6_ASM.repositories;

import com.example.Java6_ASM.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
