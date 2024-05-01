package com.example.exampleservice.user;

import com.example.commonmodule.util.NameValue;
import com.example.exampledomain.user.User;
import com.example.exampledomain.user.sdo.UserCdo;
import com.example.exampledomain.user.store.UserStore;
import com.example.exampleservice.user.sdo.UserRdo;
import com.example.exampleservice.user.sdo.UserSummaryRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStore userStore;
    public boolean saveUser(UserCdo userCdo) {
        return userStore.saveUser(userCdo);
    }

    public List<UserSummaryRdo> findAll() {
        List<User> userList = userStore.findAll();
        return userList.stream().map(UserSummaryRdo::new).collect(Collectors.toList());
    }

    public UserRdo findById(String id) {
        User user = userStore.findById(id);
        if (user == null) return null;
        return new UserRdo(user);
    }

    public Boolean checkEmail(String email) {
        List<User> usersByEmail = userStore.findByEmail(email);
        if (!CollectionUtils.isEmpty(usersByEmail)) {
            throw new IllegalArgumentException("해당 이메일에 대한 가입 정보가 존재합니다.");
        }
        return false;
    }

    public boolean modifyUser(String id, List<NameValue> nameValues) {
        User dbUser = userStore.findById(id);
        if (dbUser!=null) {
            NameValue.modifyValues(dbUser, nameValues);
        }
        return userStore.modifyUser(dbUser);
    }
}
