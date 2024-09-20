package com.example.Java6_ASM.services;

import com.example.Java6_ASM.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void createUser(User user);

    void saveAllUser(List<User> users);

    List<User> getAllUser();

    User findById(UUID id);
    void save(User user);
    void delete(UUID id);
}
