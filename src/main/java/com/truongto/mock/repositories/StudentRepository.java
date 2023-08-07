package com.truongto.mock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truongto.mock.entities.Students;

public interface StudentRepository extends JpaRepository<Students, Long>{
    
}
