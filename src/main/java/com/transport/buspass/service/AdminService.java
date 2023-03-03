package com.transport.buspass.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import com.transport.buspass.entity.Student;

public interface AdminService {

	String getDashboardPage(Model model, Integer seasonId);

	String getBuspassPage(Model model, Integer seasonId, Optional<Integer> page, Optional<Integer> size);

	String getSeasonPage(Model model);

	String createNewSeason(String seasonName);

	String deleteSeason(Integer seasonId);

	String updateSeason(Integer seasonId, String seasonName);

	String getDepartmentPage(Model model);

}
