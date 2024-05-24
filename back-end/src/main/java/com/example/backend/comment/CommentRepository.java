package com.example.backend.comment;

import com.example.backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}


//@Repository
//public interface UserRepository extends JpaRepository<User, Integer> {
//    boolean existsByEmail(String email);
//    User findByEmail(String email);
//
//    //    User findById(int id);
//    @Query("SELECT u FROM User u " +
//            "INNER JOIN Session s ON s.user.id = u.id " +
//            "WHERE s.sessionUuid = :token")
//    User findBySessionToken(@Param("token") String token);
//}