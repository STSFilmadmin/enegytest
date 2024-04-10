package com.sebn.pfm.rs485.decoder;

import java.util.Arrays;

import com.sebn.pfm.rs485.enums.DataType;
import com.sebn.pfm.rs485.enums.Endianness;
import com.sebn.pfm.rs485.model.ModbusResponse;

public class ModbusDataDecoder {

    public static ModbusResponse decodeModbusResponse(byte[] response, DataType dataType, Endianness endianness) {
        if (response.length < 3) {
            throw new IllegalArgumentException("Invalid response length for Modbus RTU.");
        }

        int deviceAddress = response[0] & 0xFF;
        int functionCode = response[1] & 0xFF;
        int byteCount = response[2] & 0xFF;
        int[] values = decodeValues(Arrays.copyOfRange(response, 3, 3 + byteCount),  dataType,  endianness); // 	decodeValues		decode16BitValues

        return new ModbusResponse(deviceAddress, functionCode, values);
    }

    public static int[] decodeValues32(byte[] data, DataType dataType1, Endianness endianness1) {
        DataType dataType = dataType1;
        Endianness endianness = endianness1;
        int dataSize = getDataSize(dataType);

        int byteSize = dataSize / 8;
        if (data.length % byteSize != 0) {
            throw new IllegalArgumentException("Invalid data length for decoding.");
        }

        int[] values = new int[data.length / byteSize];
        for (int i = 0; i < values.length; i++) {
            int value = 0;
            for (int j = 0; j < byteSize; j++) {
                int offset;
                if (endianness == Endianness.BIG_ENDIAN) {
                    offset = i * byteSize + j;
                } else {
                    offset = i * byteSize + (byteSize - 1 - j);
                }
                value |= (data[offset] & 0xFF) << ((byteSize - 1 - j) * 8);
            }

            if (dataType == DataType.BIT_32_SIGNED || dataType == DataType.BIT_64_SIGNED) {
                if ((value & (1 << (dataSize - 1))) != 0) {
                    // Розширення знаку для 32-бітних і 64-бітних значень
                    for (int k = dataSize; k < 32; k++) {
                        value |= (1 << k);
                    }
                }
            } else {
                // Маскування для беззнакових значень
                value &= (1 << dataSize) - 1;
            }




            values[i] = value;
        }
        return values;
    }
    
    /**
     * Декодує масив байтів у відповідні непідписані 16-бітні значення у Little Endian форматі.
     * Кожне 16-бітне значення у масиві байтів представлене двома байтами, де перший байт - молодший,
     * а другий - старший.
     * @param data масив байтів для декодування
     * @return масив непідписаних 16-бітних значень
     */
    public static int[] decodeValues(byte[] data, DataType dataType1, Endianness endianness1) {
        DataType dataType = dataType1;
        int dataSize = getDataSize(dataType);
   
        int byteSize ;
       // int byteSize = 2;
        if (dataSize == 16)
            byteSize = 2;
        else if (dataSize == 32)
            byteSize = 4;
        else if (dataSize == 64)
            byteSize = 8;
        else if (dataSize == 128)
            byteSize = 16;
        else
            throw new IllegalArgumentException("Unsupported data size.");

        
        
        if (data.length % byteSize != 0) {
            throw new IllegalArgumentException("Invalid data length for decoding.");
        }

        int[] values = new int[data.length / byteSize];
        for (int i = 0; i < values.length; i++) {
            int value = 0;
            for (int j = 0; j < byteSize; j++) {
                int offset;
                // Визначення правильного зміщення в залежності від порядку байтів
                switch (endianness1) {
                    case BIG_ENDIAN:
                        offset = i * byteSize + j;
                        break;
                    case LITTLE_ENDIAN:
                        offset = (i + 1) * byteSize - j - 1;
                        break;
                    case BIG_ENDIAN_BYTE_SWAP:
                    	offset = i * byteSize + (byteSize - 1 - j);
                        break;
                    case LITTLE_ENDIAN_BYTE_SWAP:
                        offset = (i + 1) * byteSize - (byteSize - 1 - j) - 1;
                        break;
                    default:
                    throw new IllegalArgumentException("Unsupported endianness.");
            }
                value |= (data[offset] & 0xFF) << (8 * (byteSize - 1 - j));
            }

            if (dataType == DataType.SIGNED|| dataType == DataType.BIT_32_SIGNED  ||dataType == DataType.BIT_64_SIGNED) {
                // Розширення знаку для 16-бітних від'ємних значень
                int signBit = 1 << (dataSize - 1);
                if ((value & signBit) != 0) {
                    // Розширення знаку вліво
                	value |= 0xFFFF0000;//<< (dataSize - 1); //0xFFFFFFFF80000000L;
                }
            } else {
                // Маскування для беззнакових значень
                value &= 0xFFFF0000; // забрати нулі для 16 біт 
            }

            values[i] = value/65536; // забрати /65536 для 16 біт 
        }
        return values;
    }


    
   private void unsigned() {
	// TODO дописати метод додатніх відємних чисел 
	   
} 
        
    

    private static int getDataSize(DataType dataType) {
        switch (dataType) {
        	case UNSIGNED:
        	case SIGNED:
        		return 16;
            case BIT_32_SIGNED:
            case BIT_32_UNSIGNED:
                return 32;
            case BIT_64_SIGNED:
            case BIT_64_UNSIGNED:
                return 64;
            default:
                throw new IllegalArgumentException("Unsupported data type.");
        }
    }
}
