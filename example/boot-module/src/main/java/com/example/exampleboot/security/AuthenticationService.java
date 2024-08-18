package com.example.exampleboot.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * 사용자 요청에 대한 인증
 * */
public class AuthenticationService {
    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    private static final String AUTH_TOKEN = "mihee";

//    public static Authentication getAuthentication(HttpServletRequest request) {
//        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
//        // 헤더에서 X-API-KEY의 값을 가져옴
//        if (apiKey == null ||!apiKey.equals(AUTH_TOKEN)) {
//            // 해당 헤더키에 대한 값이 없거나, 인증 토큰과 일치하지 않는 경우
//            throw new BadCredentialsException("Invalid API Key");
//        }
//        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
//    }


    public static Authentication getAuthentication(HttpServletRequest requset) {
        String apiKey = requset.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apiKey == null || !apiKey.equals(AUTH_TOKEN)) {
            throw new BadCredentialsException("Invalid API Key");
        }
        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}
