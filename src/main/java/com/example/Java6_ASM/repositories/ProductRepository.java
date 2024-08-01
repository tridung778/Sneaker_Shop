package com.example.Java6_ASM.repositories;

import com.example.Java6_ASM.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByCategoryId(String categoryId);

    @Query("delete from Product where name = ?1")
    void deleteByName(String productName);
}
