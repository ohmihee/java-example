package com.example.mongotemplate.repository;

import com.example.mongotemplate.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
