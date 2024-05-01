package com.example.exampledomain.user.store.storeImpl.repository;

import com.example.exampledomain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByEmail(String email);
    List<User> findByPhoneNumber(String phoneNumber);

}
