package com.example.domain.user.store;

import com.example.domain.user.AuthenticationCode;
import com.example.domain.user.sdo.AuthType;
import com.example.domain.user.sdo.AuthenticationCodeCdo;

public interface AuthenticationCodeStore {
    boolean saveCode (AuthenticationCode authenticationCode);
    boolean saveCode (AuthenticationCodeCdo authenticationCodeCdo);

    AuthenticationCode checkAuthentication(String code, String address, AuthType type);
}
