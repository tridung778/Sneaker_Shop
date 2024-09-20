package com.example.Java6_ASM.controllers;

import com.example.Java6_ASM.models.User;
import com.example.Java6_ASM.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
public class UserManagerController {
    @Autowired
    private UserRepository dao;

    @GetMapping("/admin/user")
    public List<User> getAll(Model model) {
        return dao.findAll();
    }

    @GetMapping("/admin/user/{id}")
    public User getOne(@PathVariable("id") UUID id) {
        return dao.findById(id).get();
    }

    @PostMapping("admin/user")
    public User post(@RequestBody User user) {
        dao.save(user);
        return user;
    }

    @PutMapping("/admin/user/{name}")
    public User put(@PathVariable("name") String name, @RequestBody User user) {
        dao.save(user);
        return user;
    }
    @DeleteMapping("/admin/user/{id}")
    public void delete(@PathVariable("id") UUID id) {
        dao.deleteById(id);
    }
}
