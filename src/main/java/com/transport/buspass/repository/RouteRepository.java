package com.transport.buspass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transport.buspass.entity.College;
import com.transport.buspass.entity.Route;

public interface RouteRepository extends JpaRepository<Route, Integer> {
    
}
