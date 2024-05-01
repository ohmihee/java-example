package com.example.exampledomain.user.store;

import com.example.exampledomain.user.AuthenticationCode;
import com.example.exampledomain.user.sdo.AuthType;
import com.example.exampledomain.user.sdo.AuthenticationCodeCdo;

public interface AuthenticationCodeStore {
    boolean saveCode (AuthenticationCode authenticationCode);
    boolean saveCode (AuthenticationCodeCdo authenticationCodeCdo);

    AuthenticationCode checkAuthentication(String code, String address, AuthType type);
}
