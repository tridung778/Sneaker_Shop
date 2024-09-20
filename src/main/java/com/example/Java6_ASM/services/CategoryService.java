package com.example.Java6_ASM.services;

import com.example.Java6_ASM.models.Category;

public interface CategoryService {

    Category findOrCreateCategory(String categoryName);
}
