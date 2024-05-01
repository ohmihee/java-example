package com.example.exampleservice.user.sdo;

import com.example.exampledomain.user.sdo.AuthType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationSummary {
    private String code;
    private AuthType type;
    private String address;
}
