package com.example.Java6_ASM.controllers;

import com.example.Java6_ASM.models.Product;
import com.example.Java6_ASM.repositories.ProductRepository;
import com.example.Java6_ASM.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/products")
public class ProductController {

	@Autowired
	private ProductRepository dao;

	@Autowired
	ProductService ser;

	@GetMapping()
	public List<Product> getAll(Model model) {
		return dao.findAll();
	}

	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") UUID id) {
		return dao.findById(id).get();
	}

	@PostMapping()
	public Product post(@RequestBody Product product) {
		product.setImage("/images/" + product.getImage());
		dao.save(product);
		return product;
	}

	@PutMapping("{name}")
	public Product put(@PathVariable("name") String name, @RequestBody Product product) {
		product.setImage("/images/" + product.getImage());
		dao.save(product);
		return product;
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") UUID id) {
		ser.delete(id);
	}
}
