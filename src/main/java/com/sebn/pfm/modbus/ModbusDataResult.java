package com.sebn.pfm.modbus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ModbusDataResult {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
    private String modbusSlaveName;
    private String sensorName;
    private float data;

    public ModbusDataResult(String modbusSlaveName, String sensorName, float data) {
        this.modbusSlaveName = modbusSlaveName;
        this.sensorName = sensorName;
        this.data = data;
    }

    public String getModbusSlaveName() {
        return modbusSlaveName;
    }

    public String getSensorName() {
        return sensorName;
    }

    public float getData() {
        return data;
    }

    @Override
    public String toString() {
        return modbusSlaveName +" "+ sensorName +" "+ data 
             ;
    }
//    @Override
//    public String toString() {
//    	return "ModbusDataResult{" +
//    			"modbusSlaveName='" + modbusSlaveName + '\'' +
//    			", sensorName='" + sensorName + '\'' +
//    			", data=" + data +
//    			'}';
//    }
}
