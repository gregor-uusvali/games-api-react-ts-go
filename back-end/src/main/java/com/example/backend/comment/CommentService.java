package com.example.backend.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

        return commentLikesRepository.save(commentLikes);
    }

    public List<CommentWithUserDTO> getCommentsWithLikesByPlantId(long plantId, long userId, Pageable pageable) {
        // Fetch the comments
        List<CommentWithUserDTO> comments = commentRepository.getCommentsByPlantId(plantId, userId, pageable);

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
                int status = (int) row[0];
                long count = (long) row[1];
                userStatus = (int) row[2]; // User status (1 = like, -1 = dislike, 0 = none)

                if (status == 1) {
                    likeCount = (int) count;
                    userStatus = 1; // User has liked the comment
                } else if (status == -1) {
                    dislikeCount = (int) count;
                    userStatus = -1; // User has disliked the comment
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