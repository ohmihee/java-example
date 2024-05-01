package com.example.exampledomain.common.store;

import com.example.exampledomain.common.FileUploader;
import com.example.exampledomain.common.sdo.FileUploaderCdo;

import java.util.Optional;

public interface FileUploaderStore {
    String save(FileUploaderCdo fileUploaderCdo);

    FileUploader findBySavedFileName(String newFileName);

    Optional<FileUploader> findByFileId(String id);
}
