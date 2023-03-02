package com.transport.buspass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transport.buspass.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    
}
