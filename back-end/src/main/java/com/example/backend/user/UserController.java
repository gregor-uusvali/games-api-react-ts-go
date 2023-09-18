package com.example.backend.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1") // Define the base URL for your API
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> logUserIn(@RequestBody Map<String, String> requestBody) {
        System.out.println("Request Body: " + requestBody);
        User user = userService.findByEmail(requestBody.get("email"));
        if (user == null) {
            // User does not exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Compare the provided password with the user's hashed password
        if (!userService.isPasswordMatch(requestBody.get("password"), user.getPassword())) {
            // Password does not match
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }
        String message = "Login successful";
        return ResponseEntity.ok().body("{\"userId\": \"" + user.getId() + "\"}");

    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Check if the email is already taken
        if (userService.isEmailTaken(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already taken");
        }

        // Save the user to the database
        User savedUser = userService.saveUser(user);

        // You can return a success response or the saved user object
        return ResponseEntity.ok(savedUser);
    }
}