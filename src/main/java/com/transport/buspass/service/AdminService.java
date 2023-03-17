package com.transport.buspass.service;

import org.springframework.ui.Model;

public interface AdminService {

	String getDashboardPage(Integer periodId, Model model);

	String getBuspassPage(Model model);

}
