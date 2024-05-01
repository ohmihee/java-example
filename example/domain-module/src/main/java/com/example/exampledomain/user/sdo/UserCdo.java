package com.example.exampledomain.user.sdo;

import com.example.exampledomain.user.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCdo {
    private String nickName;
    private String password;
    private String fullName;
    private String birth;
    private String profileImage;
}
