package com.sebn.pfm.rs485.util;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.sebn.pfm.rs485.model.ModbusResponse;

public class TxtWriter {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String DIRECTORY_PATH = "java/data/";

    public static void writeToTxtFile(ModbusResponse modbusResponse) {
        String fileName = generateFileName();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(formattedDateTime + " Modbus Response: " + modbusResponse.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToTxtFile(byte[] response) {
        String fileName = generateFileName();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(formattedDateTime + " Modbus response byte: " + new String(response));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToTxtFile(String string) {
        String fileName = generateFileName();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(formattedDateTime + " Modbus response string: " + string);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateFileName() {
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String directoryPath = DIRECTORY_PATH;
        createDirectory(directoryPath);
        return directoryPath + (currentDate + " modbus_responses.txt");
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
}
