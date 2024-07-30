package com.example.Java6_ASM.controllers;

import com.example.Java6_ASM.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {


    @RequestMapping("/admin/addProduct")
    public String addProduct(Model model) {
        model.addAttribute("addProduct", "admin/addProduct.html");
        return "admin-index";
    }
    @RequestMapping("/admin/account")
    public String account(Model model) {
        model.addAttribute("addProduct", "admin/account.html");
        return "admin-index";
    }

    @RequestMapping("/admin/product")
    public String product(Model model) {
        model.addAttribute("addProduct", "admin/products.html");
        return "admin-index";
    }
}
