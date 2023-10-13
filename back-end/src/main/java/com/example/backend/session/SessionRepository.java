package com.example.backend.session;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Integer> {
    Session findBySessionUuid(String sessionUuid);
    List<Session> findSessionsByUserId(int userId);
}