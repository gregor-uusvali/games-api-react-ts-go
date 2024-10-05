package com.example.backend.comment;

public class CommentWithUserDTO {
    private Comment comment;
    private String email;
    private String firstName;
    private String lastName;
    private String image;
    private int userStatus;

    // Constructor, getters, and setters
    // Constructor can take Comment and User fields

    public CommentWithUserDTO(Comment comment, String email, String firstName, String lastName, String image, int userStatus) {
        this.comment = comment;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.userStatus = userStatus;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
}

