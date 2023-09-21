package com.example.backend.session;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Integer> {
    Session findBySessionUuid(String sessionUuid);
}