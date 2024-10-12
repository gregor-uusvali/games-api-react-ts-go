package com.example.backend.comment;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @SequenceGenerator(
            name = "comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence"
    )
    private int id;
    private int userId;
    private long plantId;
    @Column(length = 1000)  // Increase varchar length to 1000 characters
    private String commentText;
    private LocalDateTime date;

    // Default constructor for JPA
    public Comment() {}

    // Constructor that matches the HQL query
    public Comment(int id, int userId, long plantId, String commentText, LocalDateTime date) {
        this.id = id;
        this.userId = userId;
        this.plantId = plantId;
        this.commentText = commentText;
        this.date = date;
    }

    // Getter and setter for id
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getPlantId() {
        return plantId;
    }

    public void setPlantId(long plantId) {
        this.plantId = plantId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date){
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", plantId='" + plantId + '\'' +
                ", commentText='" + commentText + '\'' +
                ", date=" + date +
                '}';
    }
}
