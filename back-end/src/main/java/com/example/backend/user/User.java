package com.example.backend.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime lastWatered;
    private int daysToWater;
    private int accessLevel;
    private String image;
    private LocalDateTime createdAt;

    public User(
            int id,
            String email,
            String password,
            String firstName,
            String lastName,
            LocalDateTime lastWatered,
            int daysToWater,
            int accessLevel,
            String image,
            LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastWatered = lastWatered;
        this.daysToWater = daysToWater;
        this.accessLevel = accessLevel;
        this.image = image;
        this.createdAt = createdAt;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public LocalDateTime getLastWatered() {
        return lastWatered;
    }
    public void setLastWatered(LocalDateTime lastWatered) {
        this.lastWatered = lastWatered;
    }
    public int getDaysToWater() {
        return daysToWater;
    }
    public void setDaysToWater(int daysToWater) {
        this.daysToWater = daysToWater;
    }

    public int getAccessLevel(){ return accessLevel; }
    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
    public String getImage(){ return image; }
    public void setImage(String image) {
        this.image = image;
    }
    public LocalDateTime getCreatedAt(){ return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastWatered='" + lastWatered + '\'' +
                ", daysToWater='" + daysToWater + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                ", image='" + image + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
