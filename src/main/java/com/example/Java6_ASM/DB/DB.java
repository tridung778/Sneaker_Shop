package com.example.Java6_ASM.DB;

import com.example.Java6_ASM.models.User;
import com.example.Java6_ASM.repositories.UserRepository;
import com.example.Java6_ASM.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
public class DB {

    @Bean
    CommandLineRunner initDatabase(UserService userService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Thêm user từ file user.json vào database
            String pathToUsers = "src\\main\\java\\com\\example\\Java6_ASM\\DB\\src\\user.json";
            TypeReference<List<User>> typeReference = new TypeReference<>() {
            };
            try {
                List<User> users = mapper.readValue(new File(pathToUsers), typeReference);
                userService.saveAllUser(users);
            } catch (Exception e) {
                e.printStackTrace();
            }


        };
    }
}
