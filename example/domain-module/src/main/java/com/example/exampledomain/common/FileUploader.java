package com.example.exampledomain.common;

import com.example.exampledomain.common.sdo.FileUploaderCdo;
import com.example.exampledomain.user.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FileUploader extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String originalFileName;
    private String savedFileName;
    private String fullPath;
    private String contentType;

    public FileUploader(FileUploaderCdo fileUploaderCdo) {
        BeanUtils.copyProperties(fileUploaderCdo, this);
    }


}
