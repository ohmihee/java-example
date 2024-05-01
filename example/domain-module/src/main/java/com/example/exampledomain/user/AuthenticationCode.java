package com.example.exampledomain.user;

import com.example.exampledomain.user.sdo.AuthType;
import com.example.exampledomain.user.sdo.AuthenticationCodeCdo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="authentication_code")
public class AuthenticationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String code;
    @Enumerated(EnumType.STRING)
    private AuthType type;
    private String address;
    @Column(columnDefinition = "isChecked default false")
    private boolean isChecked;

    public AuthenticationCode(AuthenticationCodeCdo authenticationCodeCdo) {
        BeanUtils.copyProperties(authenticationCodeCdo, this);
        this.isChecked = false;
    }

}
