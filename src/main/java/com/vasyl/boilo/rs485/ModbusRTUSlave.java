package com.vasyl.boilo.rs485;

import java.io.IOException;
import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;

public class ModbusRTUSlave {

    private static final int DEVICE_ADDRESS = 4;
    private static final int REGISTER_ADDRESS = 5; // Адреса регістру, з якого буде читатися дане
    private static final int DATA_VOLT = 380;
   

    public static void main(String[] args) {
        String comPort = "COM10"; // Порт, через який відбуватиметься зв'язок
        SerialPort serialPort = SerialPort.getCommPort(comPort);
        serialPort.setBaudRate(9600);
        serialPort.setNumDataBits(8);
        serialPort.setParity(SerialPort.NO_PARITY);
        serialPort.setNumStopBits(1);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 00, 0);
        System.out.println(DATA_VOLT);
        

        if (!serialPort.openPort()) {
            System.err.println("Unable to open the serial port.");
            return;
        }

        try {
            InputStream in = serialPort.getInputStream();

            while (true) {
                byte[] query = new byte[8];
                int bytesRead = in.read(query); // Очікуємо на запит

                if (bytesRead >= 6 && query[1] == 0x03 && query[2] == 0 && query[3] == REGISTER_ADDRESS && query[4] == 0 && query[5] == 1) {
                    // Якщо отримано запит на читання з регістру з адресою REGISTER_ADDRESS
                    float dataVolt = DATA_VOLT; // Отримуємо дані для відправки
                    System.out.println("convert_____");
                    byte[] response = convertFloatToBytes(dataVolt);

                    serialPort.writeBytes(response, response.length); // Надсилаємо відповідь
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serialPort.closePort();
        }
    }

    private static byte[] convertFloatToBytes(float value) {
        int intValue = Float.floatToIntBits(value);
        return new byte[] {
            (byte) ((intValue >> 24) & 0xFF),
            (byte) ((intValue >> 16) & 0xFF),
            (byte) ((intValue >> 8) & 0xFF),
            (byte) (intValue & 0xFF)
        };
    }
}
