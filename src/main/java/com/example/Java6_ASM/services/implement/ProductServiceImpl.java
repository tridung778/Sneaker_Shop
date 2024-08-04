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
    public void saveAllProduct(List<Product> products) {

    }

    @Override
    public List<Product> finAll() {
        return productRepository.findAll();
    }

    @Override
    public Product finById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public List<Product> findByCategoryId(String cid) {
        return List.of();
    }


    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(UUID id) {
        productRepository.deleteById(id);
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

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }
}
