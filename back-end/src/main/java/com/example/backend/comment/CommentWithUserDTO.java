package com.example.backend.comment;

public class CommentWithUserDTO {
    private Comment comment;
    private String email;
    private String firstName;
    private String lastName;
    private String image;
    private long likeCount;  // Updated to long
    private long dislikeCount;  // Updated to long
    private long userStatus;  // Updated to long

    // Constructor, getters, and setters
    // Constructor can take Comment and User fields

    public CommentWithUserDTO(Comment comment, String email, String firstName, String lastName, String image, long likeCount, long dislikeCount, long userStatus) {
        this.comment = comment;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.likeCount = likeCount;  // maps to COALESCE(likeCount)
        this.dislikeCount = dislikeCount;  // maps to COALESCE(dislikeCount)
        this.userStatus = userStatus;  // maps to the last field (0 in this case)
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

    public long getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }


    public long getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }


    @Override
    public String toString() {
        return "CommentWithUserDTO{" +
                "comment=" + comment.toString() +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", image=" + image +
                ", likeCount=" + likeCount +
                ", dislikeCount=" + dislikeCount +
                ", userStatus=" + userStatus +
                '}';
    }
}

