package com.transport.buspass.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.transport.buspass.dto.StudentDto;

import jakarta.validation.Valid;


public interface StudentService {

	String getFormPage(Model model);

	String saveStudent(@Valid StudentDto studentDto, BindingResult result, Model model);

	String verify(Long id, String verificationCode, Model model);
	
}
