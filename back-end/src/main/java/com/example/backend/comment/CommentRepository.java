package com.example.backend.comment;

import com.example.backend.plant.Plant;
import com.example.backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c " +
            "INNER JOIN Plant p ON p.id = c.plantId " +
            "INNER JOIN User u ON p.userId = u.id " +
            "WHERE p.id = :plantId")
    List<Comment> getCommentsByPlantId(@Param("plantId") long plantId);

//    SELECT comments.*, users.first_name, users.last_name, users.image
//    FROM comments
//    INNER JOIN plants ON plants.id = comments.plant_id
//    INNER JOIN users ON plants.user_id = users.id
//    WHERE plants.id = 1;
}