package com.example.domain.common.store.storeImpl;

import com.example.domain.common.FileUploader;
import com.example.domain.common.sdo.FileUploaderCdo;
import com.example.domain.common.store.FileUploaderStore;
import com.example.domain.common.store.storeImpl.repository.FileUploaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FileUploaderStoreImpl implements FileUploaderStore {
    private final FileUploaderRepository fileUploaderRepository;

    @Override
    public String save(FileUploaderCdo fileUploaderCdo) {
        FileUploader fileUploader = new FileUploader(fileUploaderCdo);
        FileUploader savedFile = Optional.ofNullable(fileUploaderRepository.save(fileUploader)).orElse(null);
        return savedFile != null ? savedFile.getId() : null;
    }

    @Override
    public FileUploader findBySavedFileName(String newFileName) {
        return fileUploaderRepository.findBySavedFileName(newFileName);
    }

    @Override
    public Optional<FileUploader> findByFileId(String id) {
        return fileUploaderRepository.findById(id);
    }
}
