package com.example.mongotemplate.repository.customRepository;

import com.example.mongotemplate.models.User;

import java.util.List;

public interface UserCustomRepository {
    List<User> getUserByFullNameLike(String name);
    List<User> getAll();

    List<User> etUserByAgeGreaterThan(Integer age);


}
