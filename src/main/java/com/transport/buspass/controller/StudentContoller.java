package com.transport.buspass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.transport.buspass.dto.StudentDto;
import com.transport.buspass.service.StudentService;

import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;

@Controller
public class StudentContoller {

	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/form")
	public String fromPage(Model model) {
		return studentService.getFormPage(model);
	}

	@PostMapping("/form")
	public String saveStudentForm(
			@Valid @ModelAttribute("student") StudentDto studentDto, 
			BindingResult result,
			Model model) throws JRException {

		return studentService.saveStudent(studentDto, result, model);
	}

	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}

}
