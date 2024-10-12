package com.example.backend.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Integer> {

    @Query("SELECT cl FROM CommentLikes cl WHERE cl.userId = :userId AND cl.commentId = :commentId")
    CommentLikes findByUserIdAndCommentId(@Param("userId") int userId, @Param("commentId") int commentId);
}
