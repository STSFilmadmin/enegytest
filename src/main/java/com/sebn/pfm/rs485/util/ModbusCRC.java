package com.sebn.pfm.rs485.util;

public class ModbusCRC {
        public static int calculateCRC(byte[] data, int length) {
        int crc = 0xFFFF;

        for (int i = 0; i < length; i++) {
            crc ^= (int) data[i] & 0xFF;
            for (int j = 0; j < 8; j++) {
                if ((crc & 0x0001) != 0) {
                    crc >>= 1;
                    crc ^= 0xA001;
                } else {
                    crc >>= 1;
                }
            }
        }

        return crc;
    }
        
//    	public static short calculateCRC(byte[] data, int length) {
//	    short crc = (short) 0xFFFF;
//
//	    for (int i = 0; i < length; i++) {
//	        crc ^= (short) (data[i] & 0xFF);
//	        for (int j = 0; j < 8; j++) {
//	            if ((crc & 0x0001) != 0) {
//	                crc = (short) ((crc >> 1) ^ 0xA001);
//	            } else {
//	                crc = (short) (crc >> 1);
//	            }
//	        }
//	    }
//
//	    return crc;
//	}
}
