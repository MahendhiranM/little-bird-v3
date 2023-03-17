package com.transport.buspass.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.transport.buspass.entity.Route;

public interface RouteRepository extends JpaRepository<Route, Integer> {
    
	@Query(value = "SELECT r.route_no, COUNT(s.id) AS count_of_students\n"
			+ "	FROM route r\n"
			+ "	JOIN student s ON r.id = s.route_id\n"
			+ "	GROUP BY r.id;", nativeQuery = true)
	List<Map<String, Object>> routesNoAndStudents();
	

}
