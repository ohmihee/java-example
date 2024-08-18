package com.example.domain.user;


import com.example.commonmodule.annonation.PreventModify;
import com.example.domain.user.sdo.GradeType;
import com.example.domain.user.sdo.UserCdo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String nickName;
    private String password;
    private String fullName;
    private String birth;
    private String profileImageId;
    private String phoneNumber;
    @PreventModify
    @Enumerated(EnumType.STRING)
    private GradeType grade;

    public User (UserCdo userCdo) {
        super(userCdo.getFullName());
        BeanUtils.copyProperties(userCdo, this);
        this.grade = GradeType.Silver;
    }
}
