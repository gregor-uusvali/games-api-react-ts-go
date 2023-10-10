package com.example.backend.plant;

import com.example.backend.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    @Query(value = "SELECT * FROM plants WHERE userId = :userId", nativeQuery = true)
    List<Plant> getPlantsByUserId(@Param("userId") int userId);

}
