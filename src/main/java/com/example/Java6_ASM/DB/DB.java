package com.example.Java6_ASM.DB;

import com.example.Java6_ASM.enums.Role;
import com.example.Java6_ASM.models.Account;
import com.example.Java6_ASM.models.Product;
import com.example.Java6_ASM.models.User;
import com.example.Java6_ASM.services.AccountService;
import com.example.Java6_ASM.services.ProductService;
import com.example.Java6_ASM.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.util.List;
import java.util.Locale;

@Configuration
public class DB {

    @Bean
    CommandLineRunner initDatabase(UserService userService, ProductService productService, AccountService accountService, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            int accountIndex = 20;

            // Thêm user từ file users.json vào database
            String pathToUsers = "src\\main\\java\\com\\example\\Java6_ASM\\DB\\src\\users.json";
            TypeReference<List<User>> typeUsers = new TypeReference<>() {
            };
            try {
                List<User> users = mapper.readValue(new File(pathToUsers), typeUsers);
                userService.saveAllUser(users);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Thêm product từ file products.json vào database
            String pathToProducts = "src\\main\\java\\com\\example\\Java6_ASM\\DB\\src\\products.json";
            TypeReference<List<Product>> typeProducts = new TypeReference<>() {
            };
            try {
                List<Product> products = mapper.readValue(new File(pathToProducts), typeProducts);
                products.forEach(productService::saveProduct);
//                equals
//                products.forEach(product -> {
//                    productService.saveProduct(product);
//                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Thêm account từ file accounts.json vào database
            String pathToAccounts = "src\\main\\java\\com\\example\\Java6_ASM\\DB\\src\\accounts.json";
            TypeReference<List<Account>> typeAccounts = new TypeReference<>() {
            };
            try {
                List<Account> accounts = mapper.readValue(new File(pathToAccounts), typeAccounts);
                accounts.forEach(accountService::createAccount);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Thêm account bằng thư viên faker
            for (int i = 0; i < accountIndex; i++) {
                Faker faker = new Faker(new Locale("vi"));
                Account account = new Account();
                account.setPassword(passwordEncoder.encode(faker.internet().password()));
                account.setName(faker.name().fullName());
                account.setAddress(faker.address().fullAddress());

                // Tạo username duy nhất
                String username;
                do {
                    username = faker.name().username();
                } while (accountService.isFieldExists("username", username));
                account.setUsername(username);

                // Tạo email duy nhất
                String email;
                do {
                    email = faker.internet().emailAddress();
                } while (accountService.isFieldExists("email", email));
                account.setEmail(email);

                // Tạo phone duy nhất (nếu cần)
                String phone;
                do {
                    phone = faker.phoneNumber().phoneNumber();
                } while (accountService.isFieldExists("phone", phone));
                account.setPhone(phone);

                account.setPhoto(faker.internet().image());
                account.setRole(Role.USER);

                accountService.createAccount(account);
            }

            System.out.println("Server is running...");
        };
    }
}
