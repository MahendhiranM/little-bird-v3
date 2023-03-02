package com.transport.buspass.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.transport.buspass.dto.StudentDto;
import com.transport.buspass.entity.College;
import com.transport.buspass.entity.Department;
import com.transport.buspass.entity.Route;
import com.transport.buspass.entity.Season;
import com.transport.buspass.entity.Student;
import com.transport.buspass.repository.CollegeRepository;
import com.transport.buspass.repository.DepartmentRepository;
import com.transport.buspass.repository.RouteRepository;
import com.transport.buspass.repository.SeasonRepository;
import com.transport.buspass.repository.StudentRepository;
import com.transport.buspass.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

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

	public String defaultFormData(Model model) {
		
		List<Department> departments = departmentRepository.findAll();
		List<College> colleges = collegeRepository.findAll();
		List<Route> routes = routeRepository.findAll();
		
		model.addAttribute("departments", departments);
        model.addAttribute("colleges", colleges);
        model.addAttribute("routes", routes);
        
		return "student-form";
	}
	
	@Override
	public String getFormPage(Model model) {
	
		StudentDto student = new StudentDto();
        model.addAttribute("student", student);
        defaultFormData(model);
        
		return "student-form";
	}

	@Override
	public String saveStudent(StudentDto studentDto, BindingResult result, Model model) {
		
		System.out.println(studentDto);
		
		if (result.hasErrors()) {
            model.addAttribute("student", studentDto);
            return defaultFormData(model);
        }
		
		
		Student student = new Student();
		student.setRollNo(studentDto.getRollNo());
		student.setRegisterNo(studentDto.getRegisterNo());
		student.setName(studentDto.getName());
		student.setFatherName(studentDto.getFatherName());
		student.setSemester(studentDto.getSemester());
		student.setAddress(studentDto.getAddress());
		student.setDistrict(studentDto.getDistrict());
		student.setPincode(studentDto.getPincode());
		student.setPhoneNo(studentDto.getPhoneNo());
		student.setBoardingPoint(studentDto.getBoardingPoint());
		student.setCreateAt(LocalDate.now());
		
		College college = collegeRepository.findById(studentDto.getCollegeId()).get();
		Department department = departmentRepository.findById(studentDto.getDepartmentId()).get();
		Route route = routeRepository.findById(studentDto.getRouteId()).get();
		List<Season> seasons = seasonRepository.findAll();
	
		if (college == null && department == null && route == null && seasons == null)
			return "student-form";
		
		student.setCollege(college);
		student.setDepartment(department);
		student.setRoute(route);
		
		Integer lastSeasonId = seasons.get(seasons.size() - 1).getId();
		Season lastSeason = seasonRepository.findById(lastSeasonId).get();
		student.setSeason(lastSeason);

		studentRepository.save(student);
		
		return "download-pdf";
	}
	
	
}
