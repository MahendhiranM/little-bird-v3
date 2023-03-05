package com.transport.buspass.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/admin/dashboard/buspass/delete")
	public String deleteStudent(@RequestParam("studentId") Long studentId) {
		
		return adminService.deleteStudent(studentId);
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
	
	@PostMapping("/admin/dashboard/department/create")
	public String createNewDepartment(@RequestParam("departmentName") String departmentName) {
		
		return adminService.createNewDepartment(departmentName);
	}
	
	@GetMapping("/admin/dashboard/department/delete")
	public String deleteDepartment(@RequestParam("departmentId") Integer departmentId) {
		
		return adminService.deleteDepartment(departmentId);
	}
	
	@PostMapping("/admin/dashboard/department/update")
	public String updateDepartment(
			@RequestParam("departmentId") Integer departmentId,
			@RequestParam("departmentName") String departmentName) {
		
		return adminService.updateDepartment(departmentId, departmentName);
	}
	
	@GetMapping("/admin/dashboard/route")
	public String getRoutePage(Model model) {
		
		return adminService.getRoutePage(model);
	}
	
	@PostMapping("/admin/dashboard/route/create")
	public String createNewRoute(
			@RequestParam("routeNo") Integer routeNo, 
			@RequestParam("routeName") String routeName) {
		
		return adminService.createNewRoute(routeNo, routeName);
	}
	
	@GetMapping("/admin/dashboard/route/delete")
	public String deleteRoute(@RequestParam("routeId") Integer routeId) {
		
		return adminService.deleteRoute(routeId);
	}
	
	@PostMapping("/admin/dashboard/route/update")
	public String updateRoute(
			@RequestParam("routeId") Integer routeId,
			@RequestParam("routeNo") Integer routeNo,
			@RequestParam("routeName") String routeName) {
		
		return adminService.updateRoute(routeId, routeNo, routeName);
	}
}
