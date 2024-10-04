package com.example.backend.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}