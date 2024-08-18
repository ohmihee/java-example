package com.example.domain.user.store;


import com.example.domain.user.User;
import com.example.domain.user.sdo.UserCdo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserStore {
    boolean saveUser(UserCdo userCdo);

    List<User> findAll();

    User findById(String id);

    List<User> findByEmail(String email);

    boolean modifyUser(User dbUser);

    List<User> findByPhone(String phone);
    Page<User> findAll(Pageable pageable);
}