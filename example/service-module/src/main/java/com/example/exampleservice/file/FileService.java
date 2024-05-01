package com.example.exampleservice.file;

import com.example.exampledomain.common.FileUploader;
import com.example.exampledomain.common.sdo.FileUploaderCdo;
import com.example.exampledomain.common.store.FileUploaderStore;
import com.example.exampleservice.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${upload.path:filepath}")
    private String path;
    private final FileUploaderStore fileUploaderStore;

    public String save(MultipartFile multipartFile) {
        String originalFileName = multipartFile.getOriginalFilename();
        String savedFiledName = FileUtil.createFileName(originalFileName);
        FileUploader fileUploaderDb = fileUploaderStore.findBySavedFileName(savedFiledName);
        if(fileUploaderDb != null) {
            while(fileUploaderDb.getSavedFileName().equals(savedFiledName)) {
                savedFiledName = FileUtil.createFileName(originalFileName);
            }
        }
        try {
            String fullPath = FileUtil.getFullPath(path, savedFiledName);
            multipartFile.transferTo(new File(fullPath));
            String contentType = multipartFile.getContentType();
            FileUploaderCdo fileUploaderCdo = FileUploaderCdo.builder()
                    .originalFileName(originalFileName)
                    .savedFileName(savedFiledName)
                    .contentType(contentType)
                    .fullPath(fullPath)
                    .build();
            String fileId = fileUploaderStore.save(fileUploaderCdo);
            return fileId;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public FileUploader findByFileId(String id) {
        Optional<FileUploader> fileUploader = fileUploaderStore.findByFileId(id);
        if (fileUploader.isPresent()) {
            return fileUploader.get();
        };
        return null;
    }
}


//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private String id;
//    private String originalFileName;
//    private String savedFileName;
//    private String fullPath;
//    private String contentType;