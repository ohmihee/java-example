package com.example.exampleservice.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {
    public void upload(MultipartFile file) throws IOException {


    }

//    private List<String> readExcel (MultipartFile file) throws IOException {
//        List<String> headerList = new ArrayList<>();
//
//        InputStream inputStream = file.getInputStream();
//        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//
//        XSSFSheet sheet = workbook.getSheetAt(0); // 행의 수
//        int rows = sheet.getPhysicalNumberOfRows();
//
//        for (int r = 1; r < rows-1; r++) {
//            XSSFRow row = sheet.getRow(r);
//
//            if (row != null) {
//                int cells = row.getPhysicalNumberOfCells(); // 열의 수
//
//                for (int c = 0; c < cells; c++) {
//                    XSSFCell cell = row.getCell(c);
//                    String value = "";
//
//                }
//            }
//        }
//
//
//
//    }

    public static void main(String[] args ) throws IOException {
        String filePath = "/Users/mihee/Desktop/java-example/example/service-module/e.numbers";
        String outputFile = "/Users/mihee/Desktop/java-example/example/service-module/output.xlsx";
        Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath));
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

        FileInputStream inputStream = new FileInputStream(outputFile);
        Workbook workbook1 = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook1.getSheetAt(0);




    }
}
