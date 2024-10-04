package com.example.backend.comment;

import jakarta.persistence.*;

@Entity
@Table(name = "commentLikes")
public class CommentLikes {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence"
    )
    public int id;
    public int status;
    public int commentId;
    public int userId;

    // Default constructor
    public CommentLikes() {
    }

    // Getters and setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CommentLikes{" +
                "id=" + id +
                ", userId=" + userId +
                ", commentId=" + commentId +
                ", status=" + status +
                '}';
    }
}
