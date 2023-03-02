package com.transport.buspass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transport.buspass.entity.Season;

public interface SeasonRepository extends JpaRepository<Season, Integer> {
    
}
