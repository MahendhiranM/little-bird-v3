package com.transport.buspass.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.transport.buspass.controller.StudentContoller;
import com.transport.buspass.entity.Period;
import com.transport.buspass.entity.Student;
import com.transport.buspass.repository.CollegeRepository;
import com.transport.buspass.repository.DepartmentRepository;
import com.transport.buspass.repository.PeriodRepository;
import com.transport.buspass.repository.RouteRepository;
import com.transport.buspass.repository.StudentRepository;
import com.transport.buspass.service.AdminService;
import com.transport.buspass.service.EmailService;

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
	private PeriodRepository periodRepository;

	@Autowired
	private EmailService emailService;

	Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Override
	public String getDashboardPage(Integer periodId, Model model) {
		
		List<Period> periods = periodRepository.findAll();
		List <Student> students = null;
		
		if(periodId == null) {			
			Integer id = periods.get(periods.size() - 1).getId();
			students = studentRepository.findByPeriodId(id);
		}		
		
		students = studentRepository.findByPeriodId(periodId);
		
		List<Student> temp = studentRepository.findByPeriodIdAndCreateAt(periodId, LocalDate.now());
		
		temp.stream().forEach(System.out::println);
		
		model.addAttribute("periods", periods);
		model.addAttribute("totalStudents", students.size());
		
		return "pages/admin/dashboard";
	}

	@Override
	public String getBuspassPage(Model model) {

		return "pages/admin/buspass";
	}

	
}
