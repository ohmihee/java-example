package com.example.examplecontroller.file;

import com.example.exampledomain.common.FileUploader;
import com.example.exampleservice.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@RestController()
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadByFileId(@PathVariable String id) {
        FileUploader fileUploader = fileService.findByFileId(id);
        if (fileUploader != null) {
            try {
                UrlResource resource = new UrlResource("file:"+fileUploader.getFullPath());
                String encodedOriginalFileName = UriUtils.encode(fileUploader.getOriginalFileName(), StandardCharsets.UTF_8);
                String contentDisposition = "attachment; filename=\"" + encodedOriginalFileName + "\"";
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                        .body(resource);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return ResponseEntity.ok(null);
    }
}
