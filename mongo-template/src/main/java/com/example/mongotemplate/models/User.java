package com.example.mongotemplate.models;

import com.example.mongotemplate.controller.user.dto.UserCdo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "USERS")
public class User extends BaseEntity {
    private String fullName;
    private String email;
    private String phone;
    private String nick;
    private String password;
    private int age;
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
                "User{id: %s, fullName: %s",
                this.getId(), fullName
        );
    }


}
