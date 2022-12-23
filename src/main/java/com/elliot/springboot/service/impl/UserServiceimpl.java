package com.elliot.springboot.service.impl;

import com.elliot.springboot.dto.UserDto;
import com.elliot.springboot.entity.User;
import com.elliot.springboot.mapper.UserMapper;
import com.elliot.springboot.repository.UserRepository;
import com.elliot.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceimpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        //Convert UserDto into User JPA Entity
        User user = UserMapper.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        //Convert User JPA entity to UserDto
        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName((user.getLastName()));
        existingUser.setEmail((user.getEmail()));
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
