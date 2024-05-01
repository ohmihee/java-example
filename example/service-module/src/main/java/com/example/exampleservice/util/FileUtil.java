package com.example.exampleservice.util;

import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class FileUtil {
    public static String createFileName(String originalFileName) {
        String ext = FileUtil.extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        String newFileName =  uuid + "." +ext;
        return newFileName;
    }

    public static String  extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos+1);
    }

    public static String getFullPath(String path, String fileName) {
        return path + fileName;
    }

}
