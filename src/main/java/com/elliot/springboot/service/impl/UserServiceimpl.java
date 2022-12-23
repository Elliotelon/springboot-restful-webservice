package com.elliot.springboot.service.impl;

import com.elliot.springboot.entity.User;
import com.elliot.springboot.repository.UserRepository;
import com.elliot.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceimpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
