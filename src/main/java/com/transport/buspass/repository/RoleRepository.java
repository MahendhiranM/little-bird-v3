package com.transport.buspass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transport.buspass.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByName(String name);
}
