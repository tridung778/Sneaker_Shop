package com.example.Java6_ASM.services.implement;

import com.example.Java6_ASM.models.User;
import com.example.Java6_ASM.repositories.UserRepository;
import com.example.Java6_ASM.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveAllUser(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
