package com.example.examplecontroller.excel;

import com.example.exampleservice.excel.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController()
@RequestMapping("/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;
    @GetMapping("/{Id}")
    public String retrieveExcelFileById(@PathVariable("id") String id) {
        return id;
    }
    @PostMapping("/upload")
    public String uploadExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        excelService.upload(file);

        return "safe";
    }
}
