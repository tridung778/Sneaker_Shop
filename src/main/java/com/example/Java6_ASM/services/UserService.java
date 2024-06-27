package com.example.Java6_ASM.services;

import com.example.Java6_ASM.models.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    void saveAllUser(List<User> users);

    List<User> getAllUser();
}
