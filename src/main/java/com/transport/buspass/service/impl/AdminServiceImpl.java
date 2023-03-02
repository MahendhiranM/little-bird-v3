package com.transport.buspass.service.impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.transport.buspass.entity.Season;
import com.transport.buspass.entity.Student;
import com.transport.buspass.repository.CollegeRepository;
import com.transport.buspass.repository.DepartmentRepository;
import com.transport.buspass.repository.RouteRepository;
import com.transport.buspass.repository.SeasonRepository;
import com.transport.buspass.repository.StudentRepository;
import com.transport.buspass.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private CollegeRepository collegeRepository;

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private SeasonRepository seasonRepository;

	@Override
	public String getDashboardPage(Model model, Integer seasonId) {

		List<Student> todayBuspass = studentRepository.findByCreateAt(LocalDate.now());
		List<Student> students = studentRepository.findAll();
		List<Season> seasons = seasonRepository.findAll();
		Season season = seasonRepository.findById(seasonId).orElse(null);
		
		if (! seasonId.equals(0)) 
			students = studentRepository.findBySeasonId(seasonId);

		model.addAttribute("todayBuspass", todayBuspass.size());
		model.addAttribute("seasons", seasons);
		model.addAttribute("seasonId", seasonId);
		model.addAttribute("seasonName", (season != null ? season.getName() : "All Season"));
		model.addAttribute("totalBuspass", students.size());

		return "dashboard";
	}

	@Override
	public String getBuspassPage(
			Model model, 
			Integer seasonId, 
			Optional<Integer> page, 
			Optional<Integer> size) {

		model.addAttribute("seasonId", seasonId);

		int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
		
        Page<Student> students = findPaginated(seasonId, PageRequest.of(currentPage - 1, pageSize));
 
		model.addAttribute("students", students);

		int totalPages = students.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "student-buspass";
	}

	public Page<Student> findPaginated(Integer seasonId, Pageable pageable) {

		List<Student> students = studentRepository.findBySeasonId(seasonId);

		if(seasonId.equals(0)) 
			students = studentRepository.findAll();
		
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		
		List<Student> list;

		if (students.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, students.size());
			list = students.subList(startItem, toIndex);
		}

		Page<Student> bookPage = new PageImpl<Student>(list, PageRequest.of(currentPage, pageSize), students.size());

		return bookPage;
	}

	
}
