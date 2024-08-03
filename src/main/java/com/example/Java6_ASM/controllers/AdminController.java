package com.example.Java6_ASM.controllers;

import com.example.Java6_ASM.models.Product;
import com.example.Java6_ASM.repositories.ProductRepository;
import com.example.Java6_ASM.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AdminController {
    @Autowired
    private ProductRepository dao;

    @Autowired
    private UserRepository userRepo;


    @RequestMapping("/admin/addProduct")
    public String addProduct(Model model) {
        model.addAttribute("addProduct", "admin/addProduct.html");
        return "admin-index";
    }
    @RequestMapping("/admin/addUser")
    public String addUser(Model model) {
        model.addAttribute("addProduct", "admin/user-form.html");
        return "admin-index";
    }
    @RequestMapping("/admin/account")
    public String account(Model model) {
        model.addAttribute("addProduct", "admin/admin-users.html");
        model.addAttribute("users", userRepo.findAll());
        return "admin-index";
    }

    @RequestMapping("/admin/product")
    public String product(Model model) {
        model.addAttribute("addProduct", "admin/products.html");
        return "admin-index";
    }

	@RequestMapping("/admin/index")
	public String admin(Model model) {
		return "admin-index";
	}
}
