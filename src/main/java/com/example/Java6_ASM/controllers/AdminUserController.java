package com.example.Java6_ASM.controllers;

import com.example.Java6_ASM.models.User;
import com.example.Java6_ASM.repositories.UserRepository;
import com.example.Java6_ASM.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {


    @Autowired
    private UserService userService;
//
//    @GetMapping
//    public String listUsers(Model model) {
//        List<User> users = userService.getAllUser();
//        model.addAttribute("users", users);
//        return "admin-users";
//    }
//
//    @GetMapping("/add")
//    public String showAddForm(Model model) {
//        return "user-form";
//    }
//
//    @PostMapping("/save")
//    public String saveUser(@ModelAttribute User user) {
//        userService.save(user);
//        return "redirect:/admin/users";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable("id") Long id, Model model) {
//        User user = userService.findById(id);
//        if (user == null) {
//            return "redirect:/admin/users"; // Redirect nếu không tìm thấy người dùng
//        }
//        model.addAttribute("user", user);
//        return "user-form";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.delete(id);
//        return "redirect:/admin/users";
//    }
}
