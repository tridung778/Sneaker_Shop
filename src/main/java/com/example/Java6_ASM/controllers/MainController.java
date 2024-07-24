package com.example.Java6_ASM.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("page", "components/home");
        return "index";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        return "admin-index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("page", "login/login");
        return "index";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("page", "login/signup");
        return "index";
    }

    @RequestMapping("/category")
    public String productCategory(Model model) {
        model.addAttribute("page", "product/product-category");
        return "index";
    }

    @RequestMapping("/detail")
    public String productDetail(Model model) {
        model.addAttribute("page", "product/product-detail");
        return "index";
    }
}
