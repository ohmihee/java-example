package com.example.exampleservice.user.sdo;

import com.example.domain.user.User;
import com.example.domain.user.sdo.GradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRdo {
    private String id;
    private String email;
    private String nickName;
    private String fullName;
    private String birth;
    private String profileImage;
    private GradeType grade;

    public UserRdo(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
