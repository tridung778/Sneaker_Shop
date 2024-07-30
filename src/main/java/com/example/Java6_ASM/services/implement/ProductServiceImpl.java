package com.example.Java6_ASM.services.implement;

import com.example.Java6_ASM.models.Category;
import com.example.Java6_ASM.models.Product;
import com.example.Java6_ASM.repositories.ProductRepository;
import com.example.Java6_ASM.services.CategoryService;
import com.example.Java6_ASM.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void saveProduct(Product product) {

            // Tìm hoặc tạo danh mục
            String categoryName = product.getCategory().getName();
            Category category = categoryService.findOrCreateCategory(categoryName);
            product.setCategory(category);

            // Lưu sản phẩm
            productRepository.save(product);

    }

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(UUID id) {
		return productRepository.findById(id);
	}

	@Override
	public List<Product> findByCategoryId(UUID cid) {
		return productRepository.findByCategoryId(cid);
	}
}
