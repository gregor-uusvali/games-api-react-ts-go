package com.example.backend.user;

import com.example.backend.image.ImageService;
import com.example.backend.plant.Plant;
import com.example.backend.plant.PlantRepository;
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

    private final ImageService imageService;
    private final PlantRepository plantRepository;
    public static Map<String, Session> sessions = new HashMap<>();

    @Autowired
    public UserService(UserRepository userRepository, ImageService imageService, PlantRepository plantRepository) {
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.plantRepository = plantRepository;
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
        user.setAccessLevel(2);
        user.setCreatedAt(LocalDateTime.now());
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

    @Transactional
    public void updateUserImg(int id, String image) throws IOException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update the plant's attributes
        if(image != null){

            String imagePath = existingUser.getImage();
            imageService.deleteImgFile(imagePath);
            existingUser.setImage("http://localhost:8080/images/" + image);
        }
        userRepository.save(existingUser);
    }

    public User getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return user;
    }

    public int getNumberOfUserPlants(int userId) {
        return plantRepository.getNumberOfUserPlants(userId);
    }

}
