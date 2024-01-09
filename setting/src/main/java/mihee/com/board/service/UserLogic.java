package mihee.com.board.service;

import lombok.RequiredArgsConstructor;
import mihee.com.board.controller.user.sdo.UserCdo;
import mihee.com.board.controller.user.sdo.UserRdo;
import mihee.com.board.models.user.User;
import mihee.com.board.models.user.vo.UserType;
import mihee.com.board.repository.user.UserRepository;
import mihee.com.board.repository.user.custom.UserCustomRepository;
import mihee.com.board.util.PageObj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLogic {
    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;

    public String register(UserCdo userCdo) {
        User user = User.toEntity(userCdo);
        String userId = user.getId();
        user.setUserType(UserType.MEMBER);
        User dbUser = userRepository.findById(userId).orElse(null);
        if (dbUser != null) {
            register(userCdo);
        } else {
            return userRepository.save(user).getId();
        }
        return null;
    }

    public UserRdo findUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            UserRdo userRdo = new UserRdo();
            userRdo.toRdo(user);
            return userRdo;
        } else {
          throw new RuntimeException("존재하지 않는 사용자입니다.");
        }
    }

    public PageObj findAllUser(int page, int size, String sortProperties) {
        PageObj<UserRdo> users;
        Sort sort = Sort.by(Sort.Direction.DESC, sortProperties);
        Page<UserRdo> dbUsers = userCustomRepository.findAllUserRdo(page, size, sort);
        users = new PageObj<>(dbUsers);
        return users;
    }
}
