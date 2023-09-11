package com.truongto.mock.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truongto.mock.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
