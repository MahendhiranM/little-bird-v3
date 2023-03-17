package com.transport.buspass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transport.buspass.entity.Period;

public interface PeriodRepository extends JpaRepository<Period, Integer> {
    
}
