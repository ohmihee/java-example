package com.example.exampleservice.user.sdo;

import com.example.exampledomain.user.User;
import com.example.exampledomain.user.sdo.GradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryRdo {
    private String id;
    private String nickName;
    private String fullName;
    private GradeType grade;

    public UserSummaryRdo(User user){
        BeanUtils.copyProperties(user, this);
    }
}
