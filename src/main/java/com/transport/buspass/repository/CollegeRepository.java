package com.transport.buspass.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.transport.buspass.entity.College;

public interface CollegeRepository extends JpaRepository<College, Integer> {
    
	
}
