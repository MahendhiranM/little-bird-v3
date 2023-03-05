package com.transport.buspass.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.transport.buspass.dto.StudentDto;

import net.sf.jasperreports.engine.JRException;


public interface StudentService {

	String saveStudent(StudentDto studentDto, BindingResult result, Model model) throws JRException;

	String getFormPage(Model model);
	
}
