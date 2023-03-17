package com.transport.buspass.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.transport.buspass.controller.StudentContoller;
import com.transport.buspass.dto.StudentDto;
import com.transport.buspass.entity.College;
import com.transport.buspass.entity.Department;
import com.transport.buspass.entity.Period;
import com.transport.buspass.entity.Route;
import com.transport.buspass.entity.Student;
import com.transport.buspass.repository.CollegeRepository;
import com.transport.buspass.repository.DepartmentRepository;
import com.transport.buspass.repository.PeriodRepository;
import com.transport.buspass.repository.RouteRepository;
import com.transport.buspass.repository.StudentRepository;
import com.transport.buspass.service.EmailService;
import com.transport.buspass.service.StudentService;

import jakarta.validation.Valid;

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
	private PeriodRepository periodRepository;

	@Autowired
	private EmailService emailService;

	Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	public String defaultFormData(Model model) {

		List<Department> departments = departmentRepository.findAll();
		List<College> colleges = collegeRepository.findAll();
		List<Route> routes = routeRepository.findAll();

		model.addAttribute("departments", departments);
		model.addAttribute("colleges", colleges);
		model.addAttribute("routes", routes);

		return "pages/student/form";
	}

	@Override
	public String getFormPage(Model model) {

		StudentDto student = new StudentDto();
		model.addAttribute("student", student);

		return defaultFormData(model);
	}

	@Override
	public String saveStudent(StudentDto studentDto, BindingResult result, Model model) {

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
		student.setEmail(studentDto.getEmail());
		student.setBoardingPoint(studentDto.getBoardingPoint());
		student.setCreateAt(LocalDate.now());
		student.setUpdateAt(LocalDate.now());
		student.setIsVerified(Boolean.FALSE);
		student.setVerificationCode(generateVerificationCode());

		College college = collegeRepository.findById(studentDto.getCollegeId()).orElse(null);
		Department department = departmentRepository.findById(studentDto.getDepartmentId()).orElse(null);
		Route route = routeRepository.findById(studentDto.getRouteId()).orElse(null);
		List<Period> periods = periodRepository.findAll();

		student.setCollege(college);
		student.setDepartment(department);
		student.setRoute(route);

		try {
			Integer lastPeriodId = periods.get(periods.size() - 1).getId();
			Period lastSeason = periodRepository.findById(lastPeriodId).get();
			student.setPeriod(lastSeason);
		} catch (Exception e) {
			logger.error("No period created. Hint: Create a new period.");
			return "pages/student/form-error";
		}

		Student savedStudent = studentRepository.save(student);
		model.addAttribute("email", savedStudent.getEmail());

		try {			
			emailService.sendEmail(savedStudent.getEmail(), "Little-Bird", savedStudent.getVerificationCode());
			logger.info("Email send");
		} catch (Exception e) {
			logger.error("No internet connection. hint: connect the internet.");
			return "pages/student/form-error";
		}
		
		return "pages/student/form-submitted";
	}


	private String generateVerificationCode() {

		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString();
	}

	@Override
	public String verify(Long id, String verificationCode, Model model) {
		
		if(id == null || verificationCode == null)
			return "pages/student/verify-error";
		
		Student student = studentRepository.findById(id).orElse(null);
		
		if(student != null && verificationCode.equals(student.getVerificationCode()))
			return "pages/student/verify";
		
		return "pages/student/verify-error";
	}

}
