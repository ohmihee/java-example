package com.example.mongotemplate.service;

import com.example.mongotemplate.controller.user.dto.UserCdo;
import com.example.mongotemplate.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveAll(List<UserCdo> userCdos);
    void delete(String id);

    Optional<User> findUserById(String id);

}
