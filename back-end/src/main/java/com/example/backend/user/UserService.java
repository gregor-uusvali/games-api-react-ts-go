package com.example.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Check if an email is already taken
    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) { return userRepository.findByEmail(email); }

    public boolean isPasswordMatch(String password, String hashedPW) {
        return new BCryptPasswordEncoder().matches(password, hashedPW);
    }
    // Save a new user to the database
    public User saveUser(User user) {
        String hashedPW = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPW);
        return userRepository.save(user);
    }
}
