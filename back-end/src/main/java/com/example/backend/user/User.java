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

    public User(
            int id,
            String email,
            String password,
            String firstName,
            String lastName,
            LocalDateTime lastWatered,
            int daysToWater) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastWatered = lastWatered;
        this.daysToWater = daysToWater;
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
                '}';
    }
}
