package com.example.mongotemplate.models;

import com.example.mongotemplate.controller.user.dto.UserCdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String nick;
    private String password;
    transient List<Course> courseList;
    //transient List<>

    public static User fromCdo (UserCdo userCdo) {
        User user = new User();
        BeanUtils.copyProperties(user, userCdo);
        return user;
    }

    public static List<User> fromCdo (List<UserCdo> userCdos) {
       return userCdos.stream().map(it -> fromCdo(it)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format(
                "User{id: %s, firstName: %s, lastName: %s",
                id, firstName, lastName
        );
    }


}
