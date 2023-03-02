package com.transport.buspass.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.transport.buspass.dto.StudentDto;
import com.transport.buspass.entity.Student;

import jakarta.validation.Valid;

public interface StudentService {

	String saveStudent(StudentDto studentDto, BindingResult result, Model model);

	String getFormPage(Model model);
	
}
