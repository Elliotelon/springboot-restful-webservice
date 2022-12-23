package com.elliot.springboot.service;

import com.elliot.springboot.dto.UserDto;
import com.elliot.springboot.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);

}
