package com.example.examplecontroller.excel;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/excel")
public class ExcelController {

    @GetMapping("/{Id}")
    public String retrieveExcelFileById(@PathVariable("id") String id) {
        return id;
    }
    @PostMapping("/save")
    public String save() {
        return "safe";
    }
}
