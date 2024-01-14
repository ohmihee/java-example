package com.example.mongotemplate.service;

import com.example.mongotemplate.controller.user.dto.UserCdo;

import java.util.List;

public interface UserService {
    void saveAll(List<UserCdo> userCdos);
    void delete(String id);

}
