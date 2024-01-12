package com.example.mongotemplate.controller.user;

import com.example.mongotemplate.controller.user.dto.RegisterUserDto;
import com.example.mongotemplate.controller.user.dto.UserCdo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/register")
    public void registerUsers(@RequestBody List<UserCdo> userCdos) {


    }

}
