package com.example.backend.comment;

import jakarta.persistence.*;

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
    private String commentText;
    private int likeCount;
    private int dislikeCount;
    private String date;

    public Comment() {
        // Default constructor for JPA
    }

    public Comment(int id, int userId, long plantId, String commentText, int likeCount, int dislikeCount) {
        this.id = id;
        this.userId = userId;
        this.plantId = plantId;
        this.commentText = commentText;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
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

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", plantId='" + plantId + '\'' +
                ", commentText='" + commentText + '\'' +
                ", likeCount=" + likeCount +
                ", dislikeCount=" + dislikeCount +
                '}';
    }
}
