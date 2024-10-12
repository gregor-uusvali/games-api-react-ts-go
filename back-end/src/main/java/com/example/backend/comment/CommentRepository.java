package com.example.backend.comment;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT new com.example.backend.comment.CommentWithUserDTO(c, u.email, u.firstName, u.lastName, u.image, " +
            " COALESCE((SELECT COUNT(l1) FROM CommentLikes l1 WHERE l1.commentId = c.id AND l1.status = 1), 0), " +
            " COALESCE((SELECT COUNT(l2) FROM CommentLikes l2 WHERE l2.commentId = c.id AND l2.status = -1), 0), " +
            " COALESCE((SELECT ul.status FROM CommentLikes ul WHERE ul.commentId = c.id AND ul.userId = :userId), 0)) " +
            " FROM Comment c " +
            " INNER JOIN User u ON c.userId = u.id " +
            " WHERE c.plantId = :plantId " +
            " ORDER BY c.date DESC")
    List<CommentWithUserDTO> getCommentsByPlantId(@Param("plantId") long plantId, Pageable pageable, @Param("userId") long userId);

    @Query("SELECT l.status, COUNT(l), " +
            "COALESCE((SELECT ul.status FROM CommentLikes ul WHERE ul.commentId = :commentId AND ul.userId = :userId), 0) " +
            "FROM CommentLikes l WHERE l.commentId = :commentId GROUP BY l.status")
    List<Object[]> getLikesDislikesAndUserStatus(@Param("commentId") long commentId, @Param("userId") long userId);
}