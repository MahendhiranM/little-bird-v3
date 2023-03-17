package com.transport.buspass.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transport.buspass.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

//	List<Student> findByCreateAt(LocalDate createAt);

	List<Student> findByPeriodId(Integer periodId);
	
	List<Student> findByPeriodIdAndCreateAt(Integer periodId, LocalDate date);
	
//	@Query(value = "SELECT s.route_id, COUNT(s.id) as count FROM student s GROUP BY s.route_id", nativeQuery = true)
//	List<Map<String, Object>> countByRouteId();
//	
//	@Query(value = "SELECT route.route_no, COUNT(student.id) AS count_of_students "
//			+ "FROM student JOIN route ON student.route_id = route.id "
//			+ "WHERE student.season_id =:seasonId GROUP BY student.route_id", nativeQuery = true)
//	List<Map<String, Object>> getRouteCountBySeasonId(@Param("seasonId") Integer seasonId);
//
//	@Query(value = "SELECT r.name, COUNT(s.id) AS count_of_students\n"
//			+ "FROM department r\n"
//			+ "JOIN student s ON r.id = s.department_id\n"
//			+ "WHERE s.season_id =:seasonId\n"
//			+ "GROUP BY r.id;\n"
//			+ "", nativeQuery = true)
//	List<Map<String, Object>> getDepartmentCountBySeasonId(@Param("seasonId") Integer seasonI);
	
	
}
