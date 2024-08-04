package com.example.Java6_ASM.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.Java6_ASM.models.Category;

public interface CategoryService {

    Category findOrCreateCategory(String categoryName);

	List<Category> findAll();
    
	Optional<Category> findById(UUID id);
	
	Category create(Category category);

	Category update(Category category);

	void delete(UUID id);
}
