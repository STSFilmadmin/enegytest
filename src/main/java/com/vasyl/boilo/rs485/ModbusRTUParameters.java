package com.vasyl.boilo.rs485;
import com.sebn.pfm.rs485.enums.DataType;
import com.sebn.pfm.rs485.enums.Endianness;

public class ModbusRTUParameters {
    private DataType dataType;
    private Endianness endianness;

    // Конструктор, гетери, сетери та інші методи

    public ModbusRTUParameters(DataType dataType, Endianness endianness) {
        this.dataType = dataType;
        this.endianness = endianness;
    }
    
	public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Endianness getEndianness() {
        return endianness;
    }

    public void setEndianness(Endianness endianness) {
        this.endianness = endianness;
    }
}