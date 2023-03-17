package com.transport.buspass.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transport.buspass.repository.RouteRepository;
import com.transport.buspass.repository.StudentRepository;

@RestController
@RequestMapping("/api/admin/")
public class AdminApiController {

//	@Autowired
//	private RouteRepository routeRepository;
//	
//	@Autowired
//	private StudentRepository studentRepository;
//	
//	@GetMapping("test")
//	public String test() {
//		
//		return "api worked";
//	}
//	
//	@GetMapping("route")
//	public List<Map<String, Object>> countByRouteId(
//				@RequestParam(value = "seasonId", defaultValue = "0") Integer seasonId
//			) {
//		
//		if(seasonId.equals(0))
//			return routeRepository.routesNoAndStudents();
//		
//		return studentRepository.getRouteCountBySeasonId(seasonId);
//	}
	
}
