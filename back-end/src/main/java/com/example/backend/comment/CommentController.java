package com.example.backend.comment;

import com.example.backend.plant.Plant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/comments")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "/addComment")
    public ResponseEntity<?> addComment(@RequestBody AddCommentRequest request) {
        Comment comment = new Comment();
        comment.setUserId(request.getUserId());
        comment.setCommentText(request.getCommentText());
        comment.setPlantId(request.getPlantId());
        comment.setLikeCount(0);
        comment.setDislikeCount(0);
        comment.setDate(LocalDateTime.now());

        Comment addedComment = commentService.addComment(comment);
        return ResponseEntity.ok(addedComment);
    }
}
