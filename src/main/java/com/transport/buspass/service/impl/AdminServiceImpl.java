package com.transport.buspass.service.impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

import com.transport.buspass.entity.Department;
import com.transport.buspass.entity.Route;
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
	private RouteRepository routeRepository;

	@Autowired
	private SeasonRepository seasonRepository;

	@Override
	public String getDashboardPage(Model model, Integer seasonId) {

		List<Student> todayBuspass = studentRepository.findByCreateAt(LocalDate.now());
		List<Student> students = studentRepository.findAll();
		List<Season> seasons = seasonRepository.findAll();
		Season season = seasonRepository.findById(seasonId).orElse(null);
		List<Map<String, Object>> departments = departmentRepository.departmentAndStudents();
		List<Map<String, Object>> routes = routeRepository.routesNoAndStudents();
		
		if (! seasonId.equals(0)) {
			students = studentRepository.findBySeasonId(seasonId);
			routes = studentRepository.getRouteCountBySeasonId(seasonId);
			departments = studentRepository.getDepartmentCountBySeasonId(seasonId);
		}

		model.addAttribute("todayBuspass", todayBuspass.size());
		model.addAttribute("seasons", seasons);
		model.addAttribute("seasonId", seasonId);
		model.addAttribute("seasonName", (season != null ? season.getName() : "All Season"));
		model.addAttribute("totalBuspass", students.size());
		model.addAttribute("routes", routes);
		model.addAttribute("departments", departments);

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
	
	@Override
	public String deleteStudent(Long studentId) {

		Student student = studentRepository.findById(studentId).orElse(null);

		if (student != null)
			studentRepository.deleteById(studentId);
		
		return "redirect:/admin/dashboard/buspass";
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

	@Override
	public String getSeasonPage(Model model) {
		
		List<Season> seasons = seasonRepository.findAll();
		model.addAttribute("seasons", seasons);
		
		return "season-management";
	}

	@Override
	public String createNewSeason(String seasonName) {
		
		Season season = new Season();
		season.setName(seasonName);
		season.setCreateAt(LocalDate.now());
		season.setUpdateAt(LocalDate.now());
		
		seasonRepository.save(season);
		
		return "redirect:/admin/dashboard/season";
	}

	@Override
	public String deleteSeason(Integer seasonId) {
		
		Season season = seasonRepository.findById(seasonId).orElse(null);
		
		if(season != null)
			seasonRepository.deleteById(seasonId);	
		
		return "redirect:/admin/dashboard/season";
	}

	@Override
	public String updateSeason(Integer seasonId, String seasonName) {
		
		if(seasonId != null && seasonName != null) {
			Season season = seasonRepository.findById(seasonId).orElse(null);
			if(season != null) {
				season.setName(seasonName);
				season.setUpdateAt(LocalDate.now());
				seasonRepository.save(season);
			}
		}

		return "redirect:/admin/dashboard/season";
	}

	@Override
	public String getDepartmentPage(Model model) {
		
		List<Department> departments = departmentRepository.findAll();
		model.addAttribute("departments", departments);
		
		return "department-management";
	}

	@Override
	public String createNewDepartment(String departmentName) {
		
		Department department = new Department();
		department.setName(departmentName);
		department.setCreateAt(LocalDate.now());
		department.setUpdateAt(LocalDate.now());
		
		departmentRepository.save(department);
		
		return "redirect:/admin/dashboard/department";
	}

	@Override
	public String deleteDepartment(Integer departmentId) {
		
		Department department = departmentRepository.findById(departmentId).orElse(null);

		if (department != null)
			departmentRepository.deleteById(departmentId);

		return "redirect:/admin/dashboard/department";
	}

	@Override
	public String updateDepartment(Integer departmentId, String departmentName) {
		
		if(departmentId != null && departmentName != null) {
			Department department = departmentRepository.findById(departmentId).orElse(null);
			if(department != null) {
				department.setName(departmentName);
				department.setUpdateAt(LocalDate.now());
				departmentRepository.save(department);
			}
		}
		
		return "redirect:/admin/dashboard/department";
	}

	@Override
	public String getRoutePage(Model model) {

		List<Route> routes = routeRepository.findAll();
		model.addAttribute("routes", routes);
		
		return "route-management";
	}

	@Override
	public String createNewRoute(Integer routeNo, String routeName) {
		
		Route route = new Route();
		route.setRouteNo(routeNo);
		route.setName(routeName);
		route.setCreateAt(LocalDate.now());
		route.setUpdateAt(LocalDate.now());
		
		routeRepository.save(route);
		
		return "redirect:/admin/dashboard/route";
	}

	@Override
	public String deleteRoute(Integer routeId) {
		
		Route route = routeRepository.findById(routeId).orElse(null);

		if (route != null)
			routeRepository.deleteById(routeId);
		
		return "redirect:/admin/dashboard/route";
	}

	@Override
	public String updateRoute(Integer routeId, Integer routeNo, String routeName) {
		
		if(routeId != null && routeNo != null && routeName != null) {
			Route route = routeRepository.findById(routeId).orElse(null);
			if(route != null) {
				route.setRouteNo(routeNo);
				route.setName(routeName);
				route.setUpdateAt(LocalDate.now());
				routeRepository.save(route);
			}
		}
		
		return "redirect:/admin/dashboard/route";
	}

	
	
}
