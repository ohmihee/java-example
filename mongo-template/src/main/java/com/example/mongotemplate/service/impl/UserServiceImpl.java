package com.example.mongotemplate.service.impl;

import com.example.mongotemplate.controller.user.dto.UserCdo;
import com.example.mongotemplate.models.User;
import com.example.mongotemplate.repository.UserRepository;
import com.example.mongotemplate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void saveAll(List<UserCdo> userCdos) {
        userRepository.saveAll(User.fromCdo(userCdos));
    }

    @Override
    public void delete(String id) {
        userRepository.findById(id).ifPresent(user -> userRepository.delete(user));
    }
    @Override
    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }
}
