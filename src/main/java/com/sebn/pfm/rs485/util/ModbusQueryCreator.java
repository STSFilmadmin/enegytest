package com.sebn.pfm.rs485.util;

public class ModbusQueryCreator {
    
    public static byte[] createModbusQuery(int deviceAddress, int command, int startAddress, int numberOfRegisters) {
        byte[] query = new byte[8];
        query[0] = (byte) deviceAddress;
        query[1] = (byte) command;
        query[2] = (byte) (startAddress >> 8);
        query[3] = (byte) (startAddress & 0xFF);
        query[4] = (byte) (numberOfRegisters >> 8);   
        query[5] = (byte) (numberOfRegisters & 0xFF);

        int crc = ModbusCRC.calculateCRC(query, 6);
        query[6] = (byte) (crc & 0xFF);
        query[7] = (byte) ((crc >> 8) & 0xFF);
        
        //System.out.println("Query sent: " + Arrays.toString(query));
        return query;
    }
}
