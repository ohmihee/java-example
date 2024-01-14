package com.example.mongotemplate.controller.user;

import com.example.mongotemplate.controller.user.dto.RegisterUserDto;
import com.example.mongotemplate.controller.user.dto.UserCdo;
import com.example.mongotemplate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public void registerUsers(@RequestBody List<UserCdo> userCdos) {
        userService.saveAll(userCdos);
    }

}
