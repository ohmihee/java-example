package com.example.exampledomain.common.store.storeImpl.repository;

import com.example.exampledomain.common.FileUploader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileUploaderRepository extends JpaRepository<FileUploader, String> {
    FileUploader findBySavedFileName(String savedFileName);
    boolean existsBySavedFileName(String savedFileName);
    Optional<FileUploader> findById(String id);
}
