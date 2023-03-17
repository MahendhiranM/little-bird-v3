package com.transport.buspass.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.transport.buspass.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin/dashboard")
	public String getDashboardPage(
			@RequestParam(name="periodId", required = false) Integer periodId,
			Model model) {
		
		return adminService.getDashboardPage(periodId, model);
	}
	
	@GetMapping("/admin/dashboard/buspass")
	public String getBuspassPage(Model model) {
		
		return adminService.getBuspassPage(model);
	}
}
