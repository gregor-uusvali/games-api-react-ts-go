package com.example.backend.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


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
        return commentRepository.save(comment);
    }

    public CommentLikes commentLikes(CommentLikes commentLikes) {
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
        return commentRepository.getCommentsByPlantId(plantId, pageable, userId);
    }
}