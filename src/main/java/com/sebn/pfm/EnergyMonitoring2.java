package com.sebn.pfm;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.fazecast.jSerialComm.SerialPort;
import com.sebn.pfm.modbus.ModbusDataResult;
import com.sebn.pfm.modbus.ModbusSlave;
import com.sebn.pfm.modbus.SensorData;
import com.sebn.pfm.rs485.enums.DataType;
import com.sebn.pfm.rs485.enums.Endianness;
import com.vasyl.boilo.rs485.ModbusRTUReader;

@SpringBootApplication

@Component

public class EnergyMonitoring2 {
	private static final int REQUEST_INTERVAL_MS = 4000;

	public static void main(String[] args) {
		SpringApplication.run(EnergyMonitoring2.class, args);

		System.out.println("Start");

		SensorData sensorData1 = new SensorData("voltA", 20480, 2, 3, DataType.BIT_32_UNSIGNED, Endianness.BIG_ENDIAN,
				1f, false);
		SensorData sensorData2 = new SensorData("voltB", 20482, 2, 3, DataType.BIT_32_UNSIGNED, Endianness.BIG_ENDIAN,
				1f, false);
		SensorData sensorData3 = new SensorData("voltC", 20484, 2, 3, DataType.BIT_32_UNSIGNED, Endianness.BIG_ENDIAN,
				1f, false);
		SensorData sensorData4 = new SensorData("Frequence", 20528, 2, 3, DataType.BIT_32_UNSIGNED, Endianness.BIG_ENDIAN,
				0.01f, true);
		SensorData sensorData5 = new SensorData("test2", 20486, 2, 3, DataType.BIT_32_UNSIGNED, Endianness.BIG_ENDIAN,
				1f, true);
		SensorData sensorData6 = new SensorData("test3", 20486, 2, 3, DataType.BIT_32_UNSIGNED, Endianness.BIG_ENDIAN,
				1f, true);
		SensorData sensorData7 = new SensorData("test4", 20486, 2, 3, DataType.BIT_32_UNSIGNED, Endianness.BIG_ENDIAN,
				0.01f, true);
		SensorData sensorData8 = new SensorData("test5", 20486, 2, 3, DataType.BIT_32_UNSIGNED, Endianness.BIG_ENDIAN,
				0.01f, true);
		SensorData sensorData9 = new SensorData("test6", 20492, 2, 3, DataType.BIT_32_UNSIGNED, Endianness.BIG_ENDIAN,
				0.01f, true);

		ModbusSlave modbusSlaveUV501 = new ModbusSlave("uv501", "com4", 1, Arrays.asList(sensorData1,sensorData2, sensorData3,sensorData4),
				9600, 8, SerialPort.NO_PARITY, false);

		// int[] fifa =NewModbus(modbusSlaveUV501);

		while (true) {
			List<ModbusDataResult> results = ModbusRTUReader.readModbus(modbusSlaveUV501);
			// int voltA = test[0];
			System.out.println(results);
			try {
				Thread.sleep(REQUEST_INTERVAL_MS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
