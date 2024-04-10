package com.sebn.pfm.rs485.model;

import java.util.Arrays;

public class ModbusResponse {
    private final int deviceAddress;
    private final int functionCode;
    private final int[] values;

    public ModbusResponse(int deviceAddress, int functionCode, int[] values) {
        this.deviceAddress = deviceAddress;
        this.functionCode = functionCode;
        this.values = values;
    }

    @Override
    public String toString() {
        return "{" +
                "deviceAddress=" + deviceAddress +
                ", functionCode=" + functionCode +
                ", values=" + Arrays.toString(values) +
                '}';
    }

	/**
	 * @return the deviceAddress
	 */
	public int getDeviceAddress() {
		return deviceAddress;
	}

	/**
	 * @return the functionCode
	 */
	public int getFunctionCode() {
		return functionCode;
	}

	/**
	 * @return the values
	 */
	public int[] getValues() {
		return values;
	}


    
}
