package com.transport.buspass.service;

import java.util.List;

import com.transport.buspass.dto.UserDto;
import com.transport.buspass.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
