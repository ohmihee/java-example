package com.example.exampleservice.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CodeUtil {

    public static String generateAuthCode() {
        return UUID.randomUUID().toString().substring(0,6);
    }
}
