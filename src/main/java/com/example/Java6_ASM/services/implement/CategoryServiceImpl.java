package com.example.Java6_ASM.services.implement;

import com.example.Java6_ASM.models.Category;
import com.example.Java6_ASM.repositories.CategoryRepository;
import com.example.Java6_ASM.services.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findOrCreateCategory(String categoryName) {
        return categoryRepository.findByName(categoryName).orElseGet(() -> {
            Category newCategory = new Category();
            newCategory.setName(categoryName);
            return categoryRepository.save(newCategory);
        });
    }

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
}
