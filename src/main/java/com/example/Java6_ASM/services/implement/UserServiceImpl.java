package com.example.Java6_ASM.services.implement;

import com.example.Java6_ASM.models.User;
import com.example.Java6_ASM.repositories.UserRepository;
import com.example.Java6_ASM.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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


    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElse(null); // Trả về null nếu không tìm thấy
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
