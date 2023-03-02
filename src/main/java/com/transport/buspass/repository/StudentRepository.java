package com.transport.buspass.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transport.buspass.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findByCreateAt(LocalDate createAt);
	
	List<Student> findBySeasonId(Integer id);
	
	
}
