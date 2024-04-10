package com.vasyl.boilo.rs485;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortTimeoutException;
import com.sebn.pfm.modbus.ModbusDataResult;
import com.sebn.pfm.modbus.ModbusSlave;
import com.sebn.pfm.modbus.SensorData;
import com.sebn.pfm.rs485.decoder.ModbusDataDecoder;
import com.sebn.pfm.rs485.enums.DataType;
import com.sebn.pfm.rs485.enums.Endianness;
import com.sebn.pfm.rs485.model.ModbusResponse;
import com.sebn.pfm.rs485.util.ModbusCRC;
import com.sebn.pfm.rs485.util.ModbusQueryCreator;

public class ModbusRTUReader {
    private static final int MAX_RESPONSE_LENGTH = 256;
    private static boolean portOpened = true;

    public static List<ModbusDataResult> readModbus(ModbusSlave modbusSlave) {
        List<ModbusDataResult> results = new ArrayList<>();

        String comPort = modbusSlave.getComPort();
        int deviceAddress = modbusSlave.getDeviceAddress();
        String modbusSlaveName = modbusSlave.getNameModbusSlave();
        List<SensorData> sensorDataList = modbusSlave.getSensorDataList();
       
        int baudRate = modbusSlave.getBaudR();

        SerialPort serialPort = SerialPort.getCommPort(comPort);
        serialPort.setBaudRate(baudRate);
        serialPort.setNumDataBits(8);
        serialPort.setParity(SerialPort.NO_PARITY);
        serialPort.setNumStopBits(1);

        try {
            if (!serialPort.openPort()) {
                System.err.println("Unable to open the serial port.");
            }
            portOpened = true;

            InputStream in = serialPort.getInputStream();
            for (SensorData sensorData : sensorDataList) {
                int startAddress = sensorData.getAddress();
                int numberOfRegisters = sensorData.getNumberOfRegisters();
                DataType dataType = sensorData.getDataType();
                Endianness endianness = sensorData.getEndianness();
                int command =sensorData.getCommand();
                
                byte[] query = ModbusQueryCreator.createModbusQuery(deviceAddress, command, startAddress,
                        numberOfRegisters);
                serialPort.writeBytes(query, query.length);
                Thread.sleep(100);

                byte[] responseBuffer = new byte[MAX_RESPONSE_LENGTH];
                int bytesRead = in.read(responseBuffer);

                if (bytesRead > 0) {
                    byte[] response = Arrays.copyOf(responseBuffer, bytesRead);
                    int receivedCRC = ((response[bytesRead - 1] & 0xFF) << 8) | (response[bytesRead - 2] & 0xFF);
                    int calculatedCRC = ModbusCRC.calculateCRC(response, bytesRead - 2);
                    if (receivedCRC == calculatedCRC) {
                        ModbusResponse modbusResponse = ModbusDataDecoder.decodeModbusResponse(response, dataType, endianness);
                        int[] data = modbusResponse.getValues();
                        // Multiply data by coefficient
                        float coefficient = sensorData.getCoefficient();
                        
                        // Create ModbusDataResult and add to results list
                        for (int i = 0; i < data.length; i++) {
                            float resultate= data[i] * coefficient;
                            resultate = Math.round(resultate * 10000) / 10000.0f; // Округлення до 3 знаків після коми
							ModbusDataResult result = new ModbusDataResult(modbusSlaveName, sensorData.getDataName(),resultate);
							//System.out.println(resultate);
                            results.add(result);
                            
                            
                           
                            

                        }
                    } else {
                        System.err.println("CRC mismatch: Received " + receivedCRC + ", calculated " + calculatedCRC);
                    }
                } else {
                    System.err.println("No response received.");
                }
            }
        } catch (SerialPortTimeoutException e) {
            System.out.println("Error: замітка " + e.getMessage());
            if (portOpened) {
                serialPort.closePort();
                portOpened = false;
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (portOpened) {
                serialPort.closePort();
                portOpened = false;
            }
        }
        return results;
    }

    public static String colorize(String message, String color) {
        return color + message + "\u001B[0m";
    }
    
    
    
}











































//

