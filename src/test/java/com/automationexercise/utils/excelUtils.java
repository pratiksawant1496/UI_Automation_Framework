package com.automationexercise.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class excelUtils {

    // Load workbook from resources
    private static Workbook loadExcel(String resourcePath) throws IOException {
        InputStream inputStream = excelUtils.class.getClassLoader().getResourceAsStream(resourcePath);

        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }

        return new XSSFWorkbook(inputStream);
    }

    // Generic method to fetch cell data
    private static String getCellData(String resourcePath, String sheetName, int rowNum, int colNum) throws IOException {
        Workbook workbook = loadExcel(resourcePath);
        Sheet sheet = workbook.getSheet(sheetName);

        DataFormatter formatter = new DataFormatter();
        String value = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));

        workbook.close();
        return value;
    }

    // Return entire row as Map<Header, Value>
    public static Map<String, String> getRowData(String resourcePath, String sheetName, int rowNum) throws IOException {
        Workbook workbook = loadExcel(resourcePath);
        Sheet sheet = workbook.getSheet(sheetName);

        Row headerRow = sheet.getRow(0); // assume first row contains headers
        Row dataRow = sheet.getRow(rowNum);

        if (headerRow == null || dataRow == null) {
            workbook.close();
            throw new IOException("Header or data row not found");
        }

        Map<String, String> rowData = new HashMap<>();
        DataFormatter formatter = new DataFormatter();

        for (int col = 0; col < headerRow.getLastCellNum(); col++) {
            String header = formatter.formatCellValue(headerRow.getCell(col));
            String value = formatter.formatCellValue(dataRow.getCell(col));
            rowData.put(header, value);
        }

        workbook.close();
        return rowData;
    }

    //  PaymentDetails.xlsx convenience method
    public static Map<String, String> paymentDetails(int rowNum) throws IOException {
        return getRowData("testData/PaymentDetails.xlsx", "Sheet1", rowNum);
    }

}