package com.example.exampledomain.common.sdo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FileUploaderCdo {
    private String originalFileName;
    private String savedFileName;
    private String fullPath;
    private String contentType;
}
