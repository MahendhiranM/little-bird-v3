package com.transport.buspass.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
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

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

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
	public String saveStudent(StudentDto studentDto, BindingResult result, Model model) throws JRException {

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

		Student savedStudent = studentRepository.save(student);
		
		System.out.println(savedStudent);
		
		// Pdf generating
		byte[] pdf = createPdf(savedStudent);
		String pdfData = Base64.getEncoder().encodeToString(pdf);
		model.addAttribute("pdfData", pdfData);

		return "download-pdf";
	}

	public byte[] createPdf(Student savedStudent) throws JRException {
		JasperReport jasperReport = JasperCompileManager.compileReport(
										getClass().getResourceAsStream("/reports/report.jrxml")
									);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", savedStudent.getName());
		parameters.put("rollNo", savedStudent.getRollNo());
		parameters.put("collegeName", savedStudent.getCollege().getName());
		parameters.put("phoneNo", savedStudent.getPhoneNo());
		parameters.put("boardingPoint", savedStudent.getBoardingPoint());
		parameters.put("address", savedStudent.getAddress());
		parameters.put("fatherName", savedStudent.getFatherName());
		parameters.put("city", savedStudent.getDistrict());
		parameters.put("pincode", savedStudent.getPincode());
		parameters.put("semester", savedStudent.getSemester());
		parameters.put("yearOfStudy", 2);
		parameters.put("department", savedStudent.getDepartment().getName());
		parameters.put("routeNo", savedStudent.getRoute().getRouteNo());

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}
