package com.example.Java6_ASM.repositories;

import com.example.Java6_ASM.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
