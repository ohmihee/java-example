package com.example.exampleboot.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 사용자 지정 인증 필터 관련
 * */
public class AuthenticationFilter extends GenericFilterBean {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        try {
//            Authentication authentication = AuthenticationService.getAuthentication((HttpServletRequest) request);
//            // 서버 요청 값에서 인증 관련 부분을 꺼내옴
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//           // 인증 객체를 현재 실행 중인 스레드의 보안 컨텍스트에 설정
//        }catch (Exception e) {
//            HttpServletResponse httpResponse =  (HttpServletResponse) response;
//            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // SC_UNAUTHORIZED -> 401
//
//            httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            PrintWriter writer = httpResponse.getWriter();
//            writer.println(e.getMessage());
//            writer.flush();
//            writer.close();
//        }
//        chain.doFilter(request, response);
//    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException {
        try {
            Authentication authentication= AuthenticationService.getAuthentication((HttpServletRequest) request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = httpResponse.getWriter();
            writer.print(e.getMessage());
            writer.flush();
            writer.close();
        }
    }
}
