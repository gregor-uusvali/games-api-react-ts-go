package com.example.backend.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.backend.comment.CommentWithUserDTO;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT new com.example.backend.comment.CommentWithUserDTO(c, u.email, u.firstName, u.lastName, u.image) FROM Comment c " +
            "INNER JOIN User u ON c.userId = u.id " +
            "WHERE c.plantId = :plantId " +
            "ORDER BY c.date DESC")
    List<CommentWithUserDTO> getCommentsByPlantId(@Param("plantId") long plantId);
//    pageination part - TODO
//    Page<CommentWithUserDTO> getCommentsByPlantId(@Param("plantId") long plantId, Pageable pageable);


}