package com.transport.buspass.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.transport.buspass.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    
	@Query(value = "SELECT r.name, COUNT(s.id) AS count_of_students\n"
			+ "	FROM department r\n"
			+ "	JOIN student s ON r.id = s.department_id\n"
			+ "	GROUP BY r.id", nativeQuery = true)
	List<Map<String, Object>> departmentAndStudents();
	
	

}
