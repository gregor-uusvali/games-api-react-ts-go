package com.example.backend.user;

import com.example.backend.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    public User findByEmail(String email) { return userRepository.findByEmail(email); }

    public User findBySessionToken(String token){ return userRepository.findBySessionToken(token);}

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
