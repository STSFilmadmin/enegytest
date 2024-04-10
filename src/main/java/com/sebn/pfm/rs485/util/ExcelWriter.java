package com.sebn.pfm.rs485.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sebn.pfm.rs485.model.ModbusResponse;

public class ExcelWriter {

    private static final String DIRECTORY_PATH = "java/data/"; // Директорія для збереження файлу Excel
    private static final String FILE_EXTENSION = ".xlsx"; // Розширення файлу Excel
    private static final String SHEET_NAME = "Modbus Responses";
    private static final String[] VALUE_LABELS = {"L1", "l2", "l3", "L1-L2", "L2-L3", "L3-L2", "A1", "A2", "A3", "A Neutral"};

    public static void writeToExcel(ModbusResponse modbusResponse) {
        createDirectory(DIRECTORY_PATH); // Перевірка наявності директорії

        String fileName = generateFileName(); // Генерування імені файлу Excel
        try (Workbook workbook = getWorkbook(fileName)) {
            Sheet sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) {
                sheet = workbook.createSheet(SHEET_NAME);
                createHeaderRows(sheet);
            }

            // Створення рядка даних
            int currentRow = sheet.getLastRowNum() + 1;
            Row dataRow = sheet.createRow(currentRow);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = LocalDateTime.now().format(formatter);
            dataRow.createCell(0).setCellValue(formattedDateTime);
            dataRow.createCell(1).setCellValue(modbusResponse.getDeviceAddress());
            dataRow.createCell(2).setCellValue(modbusResponse.getFunctionCode());

            // Створення окремої комірки для кожного значення
            int[] values = modbusResponse.getValues();
            for (int i = 0; i < values.length; i++) {
                dataRow.createCell(3 + i).setCellValue(values[i]);
            }

            // Запис до файлу
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Workbook getWorkbook(String fileName) throws IOException {
        Workbook workbook;
        try {
            workbook = WorkbookFactory.create(new FileInputStream(fileName));
        } catch (IOException e) {
            workbook = new XSSFWorkbook();
        }
        return workbook;
    }

    private static void createHeaderRows(Sheet sheet) {
        // Перший заголовковий рядок для "Values"
        Row headerRow1 = sheet.createRow(0);
        headerRow1.createCell(3).setCellValue("Values");

        // Другий заголовковий рядок для "Date/Time", "Device Address", "Function Code", та міток для "Values"
        Row headerRow2 = sheet.createRow(1);
        headerRow2.createCell(0).setCellValue("Date/Time");
        headerRow2.createCell(1).setCellValue("Device Address");
        headerRow2.createCell(2).setCellValue("Function Code");

        // Мітки для "Values"
        for (int i = 0; i < VALUE_LABELS.length; i++) {
            headerRow2.createCell(3 + i).setCellValue(VALUE_LABELS[i]);
        }
    }

    private static void createDirectory(String directoryPath) {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String generateFileName() {
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return DIRECTORY_PATH + currentDate + " modbus_responses" + FILE_EXTENSION;
    }
}
