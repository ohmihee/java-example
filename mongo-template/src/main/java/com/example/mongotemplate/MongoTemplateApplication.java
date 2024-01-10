package com.example.mongotemplate;

import com.example.mongotemplate.models.User;
import com.example.mongotemplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoTemplateApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongoTemplateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
        userRepository.deleteAll();;
        userRepository.save(new User());

    }

}
