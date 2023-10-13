package com.example.backend.session;

import com.example.backend.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "session_uuid", nullable = false)
    private String sessionUuid;

    private LocalDateTime createdAt;
    private LocalDateTime lastSeen;

    public void setUser(User user) {
        this.user = user;
    }
    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;

    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;

    }
    public User getUser() {
        return user;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public void setSessionUuid(String sessionUuid) {
        this.sessionUuid = sessionUuid; // Add this setter
    }
    public String getSessionUuid(){
        return sessionUuid;
    }
    @Override
    public String toString() {
        return "Session ID: " + this.sessionUuid + ", User ID: " + this.user;
    }
}
