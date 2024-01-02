package mihee.com.controller.user;

import lombok.RequiredArgsConstructor;
import mihee.com.controller.user.sdo.UserCdo;
import mihee.com.controller.user.sdo.UserRdo;
import mihee.com.service.UserLogic;
import mihee.com.util.PageObj;
import org.springframework.web.bind.annotation.*;

@RestController("/api/user")
@RequiredArgsConstructor
public class UserController {
//    private final UserLogic userLogic;
//    @PostMapping("/register")
//    public String registerUser(@RequestBody UserCdo userCdo) {
//     return userLogic.register(userCdo);
//    }
//
//    @GetMapping("/mypage/{id}")
//    public UserRdo findUserById(@PathVariable String id) {
//        return userLogic.findUserById(id);
//    }
//    @GetMapping("/page")
//    public PageObj findAllUser(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sort") String sort) {
//        return userLogic.findAllUser(page, size, sort);
//    }
}
