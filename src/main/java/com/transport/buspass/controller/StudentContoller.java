package com.transport.buspass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.transport.buspass.dto.StudentDto;
import com.transport.buspass.service.StudentService;

import jakarta.validation.Valid;

@Controller
public class StudentContoller {

	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	public String getHomePage(Model model) {
		return "index";
	}

	@GetMapping("/form")
	public String getFormPage(Model model) {

		return studentService.getFormPage(model);
	}

	@PostMapping("/form")
	public String saveStudent(@Valid @ModelAttribute("student") StudentDto studentDto, BindingResult result,
			Model model) {

		return studentService.saveStudent(studentDto, result, model);
	}

	@GetMapping("/verify")
	public String verify(@RequestParam("id") Long id, @RequestParam("verificationCode") String verificationCode,
			Model model) {

		return studentService.verify(id, verificationCode, model);
	}

}
