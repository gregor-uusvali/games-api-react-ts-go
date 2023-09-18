package com.example.backend.plant;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PlantRepository extends JpaRepository<Plant, Long> {
//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE plants SET name = ?1, description = ?2, image = ?3, instruction = ?4 WHERE id = ?5", nativeQuery = true)
//    void updatePlantById(String name, String description, String image, String instruction, Long id);

}
