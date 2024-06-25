package com.example.Java6_ASM.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("message", "Hello World!");
        return "index";
    }
}
