package com.example.backend.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentLikesRepository commentLikesRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentLikesRepository commentLikesRepository) {
        this.commentRepository = commentRepository;
        this.commentLikesRepository = commentLikesRepository;
    }

    public Comment addComment(Comment comment) {
        System.out.println(comment);
        return commentRepository.save(comment);
    }

    public CommentLikes commentLikes(CommentLikes commentLikes) {
        System.out.println("############################");
        System.out.println(commentLikes);
        System.out.println("############################");
        int userId = commentLikes.getUserId();
        int commentId = commentLikes.getCommentId();

        // Check if a CommentLikes entry already exists
        CommentLikes existingCommentLike = commentLikesRepository.findByUserIdAndCommentId(userId, commentId);

        if (existingCommentLike != null) {
            // If it exists, update the existing entry
            existingCommentLike.setStatus(commentLikes.getStatus()); // Update status or any other field
            return commentLikesRepository.save(existingCommentLike);
        } else {
            // If it doesn't exist, save the new entry
            return commentLikesRepository.save(commentLikes);
        }
    }

    public List<CommentWithUserDTO> getCommentsWithLikesByPlantId(long plantId, long userId, Pageable pageable) {
        // Fetch the comments
        List<CommentWithUserDTO> comments = commentRepository.getCommentsByPlantId(plantId, pageable);
        System.out.println("#######################");
        System.out.println("Userid: " + userId);
        System.out.println("#######################");
        // For each comment, fetch like/dislike info
        for (CommentWithUserDTO comment : comments) {
            long commentId = comment.getComment().getId(); // Assuming CommentWithUserDTO has a Comment field

            // Fetch likes, dislikes, and user status
            List<Object[]> likeData = commentRepository.getLikesDislikesAndUserStatus(commentId, userId);

            int likeCount = 0;
            int dislikeCount = 0;
            int userStatus = 0; // Default to no reaction

            // Process the like data
            for (Object[] row : likeData) {
                System.out.println(Arrays.toString(row));
                int status = (int) row[0];  // This is the like/dislike status (1 or -1)
                long count = (long) row[1];  // This is the like/dislike count
                userStatus = (int) row[2];  // This is the user's like/dislike status (1, -1, or 0)

                if (status == 1) {
                    likeCount = (int) count;  // Set the like count
                } else if (status == -1) {
                    dislikeCount = (int) count;  // Set the dislike count
                }
            }
            // Set like, dislike, and user status info into the CommentWithUserDTO
            comment.getComment().setLikeCount(likeCount);
            comment.getComment().setDislikeCount(dislikeCount);
            comment.setUserStatus(userStatus);
        }

        return comments;
    }

}