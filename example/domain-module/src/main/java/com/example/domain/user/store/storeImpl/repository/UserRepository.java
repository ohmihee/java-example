package com.example.domain.user.store.storeImpl.repository;

import com.example.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByEmail(String email);
    List<User> findByPhoneNumber(String phoneNumber);

}
