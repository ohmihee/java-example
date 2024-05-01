package com.example.examplecontroller.user;

import com.example.commonmodule.util.NameValue;
import com.example.exampledomain.user.sdo.UserCdo;
import com.example.exampleservice.user.UserService;
import com.example.exampleservice.user.sdo.AuthenticationSummary;
import com.example.exampleservice.user.sdo.UserRdo;
import com.example.exampleservice.user.sdo.UserSummaryRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping()
    public List<UserSummaryRdo> findAllUser() {
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserRdo> findById(@PathVariable String id) {
        UserRdo userRdo = userService.findById(id);
        if (userRdo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.findById(id));
    }
    @GetMapping("/authentication/{email}")
    public ResponseEntity<Boolean> sendEmailAuthentication(@PathVariable String email) {
        Boolean result = userService.sendEmailAuthentication(email);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/authentication/check")
    public ResponseEntity<Boolean> checkAuthentication(@RequestBody AuthenticationSummary authenticationSummary) {
        Boolean result = userService.checkAuthentication(authenticationSummary);
        return ResponseEntity.ok(result);

    }
    @PostMapping("/enrollment")
    public boolean saveUser(@RequestBody UserCdo userCdo) {
        return userService.saveUser(userCdo);
    }

    @PutMapping("/edit/{id}")
    public boolean modifyUser(@RequestBody List<NameValue> nameValues, @PathVariable String id) {
        return userService.modifyUser(id, nameValues);
    }
}
