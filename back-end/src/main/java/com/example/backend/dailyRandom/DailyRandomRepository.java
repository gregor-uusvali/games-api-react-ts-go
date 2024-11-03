package com.example.backend.dailyRandom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyRandomRepository extends JpaRepository<DailyRandom, Integer> {

}
