package com.sebn.pfm.Energy.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sebn.pfm.modbus.ModbusSlave;
import com.sebn.pfm.modbus.SensorData;
import com.sebn.pfm.repositories.ModbusSlaveRepository;
import com.sebn.pfm.repositories.SensorDataRepository;
import com.sebn.pfm.rs485.enums.DataType;
import com.sebn.pfm.rs485.enums.Endianness;

@Controller

//@RequestMapping("/sensor-data")
public class SensorDataController {

	private final SensorDataRepository sensorDataRepository;
    private final ModbusSlaveRepository modbusSlaveRepository; // Додайте це поле

    @Autowired
    public SensorDataController(SensorDataRepository sensorDataRepository, ModbusSlaveRepository modbusSlaveRepository) {
        this.sensorDataRepository = sensorDataRepository;
        this.modbusSlaveRepository = modbusSlaveRepository; // Ініціалізуйте це поле через конструктор
    }

    @PostMapping(path = "/addsensorData")
    public String addSensorData(
    		@RequestParam String dataName, 
    		@RequestParam int address,
            @RequestParam int numberOfRegisters, 
            @RequestParam int command,
            @RequestParam DataType dataType, 
            @RequestParam Endianness endianness,
            @RequestParam float coefficient,
            @RequestParam boolean saveToDatabase,
             Model model) {

    	SensorData sensorData = new SensorData(dataName, address, numberOfRegisters, command, dataType, endianness, coefficient, saveToDatabase);

		sensorDataRepository.save(sensorData);

Iterable<SensorData> sensorDatas = sensorDataRepository.findAll();
model.addAttribute("sensorData", sensorDatas);
System.out.println("вивід " + sensorDatas);

return "modbus";
}
    @GetMapping("/modbus")
    public String modbus(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        Iterable<SensorData> sensorDatas = sensorDataRepository.findAll();
        Iterable<ModbusSlave> modbusSlaves = modbusSlaveRepository.findAll();
        System.out.println("++++++++++++++++++++++"+ modbusSlaves);
        System.out.println("----------------------"+ sensorDatas);

        model.addAttribute("sensorData", sensorDatas);
        model.addAttribute("modbusSlaves", modbusSlaves);
        
        return "modbus";
    }
   
    
    
}
