package com.example.backend.user;

import com.example.backend.plant.Plant;
import com.example.backend.session.Session;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;
    public static Map<String, Session> sessions = new HashMap<>();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Check if an email is already taken
    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findBySessionToken(String token) {
        User user = userRepository.findBySessionToken(token);
        user.setPassword("");
        user.setEmail("");
        return user;
    }

    public boolean isPasswordMatch(String password, String hashedPW) {
        return new BCryptPasswordEncoder().matches(password, hashedPW);
    }

    // Save a new user to the database
    public User saveUser(User user) {
        String hashedPW = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPW);
        user.setLastWatered(LocalDateTime.now());
        user.setDaysToWater(10);
        return userRepository.save(user);

    }

    @Transactional
    public LocalDateTime updateUserWatered(int userId) throws IOException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        existingUser.setLastWatered(LocalDateTime.now());

        userRepository.save(existingUser);
        return existingUser.getLastWatered();
    }

    @Transactional
    public int updateUserDaysToWater(int userId, int days) throws IOException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        existingUser.setDaysToWater(days);

        userRepository.save(existingUser);
        return existingUser.getDaysToWater();
    }

}
