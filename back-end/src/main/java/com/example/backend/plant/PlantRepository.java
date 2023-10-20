package com.example.backend.plant;

import com.example.backend.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    @Query("SELECT p FROM Plant p " +
            "INNER JOIN Session s ON s.user.id = p.userId " +
            "WHERE s.sessionUuid = :token")
    List<Plant> getPlantsBySessionToken(@Param("token") String token);
    @Query("SELECT COUNT(p) FROM Plant p WHERE p.userId = :userId")
    int getNumberOfUserPlants(@Param("userId") int userId);
}
