package com.transport.buspass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transport.buspass.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
