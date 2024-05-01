package com.example.exampledomain.user.store;


import com.example.exampledomain.user.User;
import com.example.exampledomain.user.sdo.AuthType;
import com.example.exampledomain.user.sdo.UserCdo;

import java.util.List;

public interface UserStore {
    boolean saveUser(UserCdo userCdo);

    List<User> findAll();

    User findById(String id);

    List<User> findByEmail(String email);

    boolean modifyUser(User dbUser);

    List<User> findByPhone(String phone);
}