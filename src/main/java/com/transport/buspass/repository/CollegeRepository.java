package com.transport.buspass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transport.buspass.entity.College;

public interface CollegeRepository extends JpaRepository<College, Integer> {
    
}
