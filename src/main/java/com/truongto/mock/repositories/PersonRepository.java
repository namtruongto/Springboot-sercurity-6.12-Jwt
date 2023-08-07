package com.truongto.mock.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truongto.mock.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String username);
}
