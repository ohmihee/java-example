package com.example.mongotemplate.service.impl;

import com.example.mongotemplate.controller.user.dto.UserCdo;
import com.example.mongotemplate.models.User;
import com.example.mongotemplate.repository.UserRepository;
import com.example.mongotemplate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public void saveAll(List<UserCdo> userCdos) {
        userRepository.saveAll(User.fromCdo(userCdos));
    }
}
