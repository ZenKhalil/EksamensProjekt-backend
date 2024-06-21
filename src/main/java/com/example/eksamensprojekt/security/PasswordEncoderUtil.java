package com.example.eksamensprojekt.security;

import com.example.eksamensprojekt.model.User;
import com.example.eksamensprojekt.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

@Component
public class PasswordEncoderUtil implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordEncoderUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        encodeExistingPasswords();
    }

    private void encodeExistingPasswords() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }
    }
}
