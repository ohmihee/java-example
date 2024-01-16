package com.example.mongotemplate.controller.user;

import com.example.mongotemplate.controller.user.dto.RegisterUserDto;
import com.example.mongotemplate.controller.user.dto.UserCdo;
import com.example.mongotemplate.models.User;
import com.example.mongotemplate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public void registerUsers(@RequestBody List<UserCdo> userCdos) {
        userService.saveAll(userCdos);
    }

    @GetMapping("/{id}")
    public Optional<User> findUserById(@PathVariable  String id) {
        return userService.findUserById(id);
    }

}
