package com.example.domain.common.store;

import com.example.domain.common.FileUploader;
import com.example.domain.common.sdo.FileUploaderCdo;

import java.util.Optional;

public interface FileUploaderStore {
    String save(FileUploaderCdo fileUploaderCdo);

    FileUploader findBySavedFileName(String newFileName);

    Optional<FileUploader> findByFileId(String id);
}
