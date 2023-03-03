package com.transport.buspass.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.transport.buspass.entity.Student;
import com.transport.buspass.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin/dashboard")
	public String dashboard(
			@RequestParam(value = "seasonId" , defaultValue = "0") Integer seasonId,
			Model model) {
		
		return adminService.getDashboardPage(model, seasonId);
	}
	
	@GetMapping("/admin/dashboard/buspass")
	public String dashboardAndBuspass(
			@RequestParam(value = "seasonId" , defaultValue = "0") Integer seasonId,
			@RequestParam("page") Optional<Integer> page, 
		    @RequestParam("size") Optional<Integer> size,
			Model model) {
        
		return adminService.getBuspassPage(model, seasonId, page, size);
	}
	
	@GetMapping("/admin/dashboard/season")
	public String getSeasonPage(Model model) {
		
		return adminService.getSeasonPage(model);
	}

	@PostMapping("/admin/dashboard/season/create")
	public String createNewSeason(@RequestParam("seasonName") String seasonName) {
		
		return adminService.createNewSeason(seasonName);
	}
	
	@GetMapping("/admin/dashboard/season/delete")
	public String deleteSeason(@RequestParam("seasonId") Integer seasonId) {
		
		return adminService.deleteSeason(seasonId);
	}
	
	@PostMapping("/admin/dashboard/season/update")
	public String updateSeason(
			@RequestParam("seasonId") Integer seasonId,
			@RequestParam("seasonName") String seasonName) {
		
		return adminService.updateSeason(seasonId, seasonName);
	}
	
	@GetMapping("/admin/dashboard/department")
	public String getDepartmentPage(Model model) {
		
		return adminService.getDepartmentPage(model);
	}
}
