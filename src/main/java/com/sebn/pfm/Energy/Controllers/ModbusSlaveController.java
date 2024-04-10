package com.sebn.pfm.Energy.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sebn.pfm.modbus.ModbusDataResult;
import com.sebn.pfm.modbus.ModbusSlave;
import com.sebn.pfm.modbus.SensorData;
import com.sebn.pfm.repositories.ModbusSlaveRepository;
import com.sebn.pfm.repositories.SensorDataRepository;
import com.vasyl.boilo.rs485.ModbusRTUReader;

@Controller
//@RestController
//@RequestMapping("/modbus-slaves")
public class ModbusSlaveController {

	private final ModbusSlaveRepository modbusSlaveRepository;

	@Autowired
	public ModbusSlaveController(ModbusSlaveRepository modbusSlaveRepository) {
		this.modbusSlaveRepository = modbusSlaveRepository;
	}

	@Autowired
	private SensorDataRepository sensorDataRepository;

	@GetMapping("/modbus.html")
	public String modbus2(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		Iterable<SensorData> sensorData;
		sensorData = sensorDataRepository.findAll();
		model.addAttribute("sensorData", sensorData);

		Iterable<ModbusSlave> modbusSlaves = modbusSlaveRepository.findAll();
		System.out.println("++++++++++++++++++++++" + modbusSlaves);

		model.addAttribute("modbusSlaves", modbusSlaves);
		return "modbus";
	}

	@PostMapping("/getidmodbus")
	public String postidmodbus(@RequestParam(name = "id") Long id, Model model) {
		Optional<ModbusSlave> modbusSlaveOptional = modbusSlaveRepository.findById(id);
		if (modbusSlaveOptional.isPresent()) {
			ModbusSlave modbusSlave = modbusSlaveOptional.get();
			model.addAttribute("modbusSlave", modbusSlave);
		}
		return "modbus";
	}

	@PostMapping("/filter2")
	public String filter2(@RequestParam("filter") String filter, Model model) {
		Iterable<ModbusSlave> modbusSlaves;

		if (filter != null && !filter.isEmpty()) {
			modbusSlaves = modbusSlaveRepository.findByNameModbusSlave(filter);
		} else {
			modbusSlaves = modbusSlaveRepository.findAll();
		}

		model.addAttribute("modbusSlaves", modbusSlaves);

		return "modbus";
	}

	@PostMapping("/getdataidmodbus")
	// @ResponseBody
	public String getdataidmodbus(@RequestParam(name = "id") Long id, Model model) {
		List<ModbusDataResult> data = null;
		ModbusSlave modbusSlave = modbusSlaveRepository.findById(id).orElse(null);
		if (modbusSlave != null) {
			data = ModbusRTUReader.readModbus(modbusSlave);
			Iterable<ModbusSlave> modbusSlaves;
			modbusSlaves = modbusSlaveRepository.findAll();
			model.addAttribute("modbusSlaves", modbusSlaves);
		} else {
			// Обробка ситуації, коли об'єкт modbusSlave не знайдено
		}
		// model.addAttribute("data", data);
		System.out.println(data);
		return "modbus";

	}

	@PostMapping("/addModbusSlave")
	public String addModbusSlave(@RequestParam String nameModbusSlave, @RequestParam String comPort,
			@RequestParam int deviceAddress, @RequestParam int baudR, @RequestParam int numDataBits,
			@RequestParam int parity, @RequestParam boolean comPortTimeouts, @RequestParam List<Long> sensorDataIds,
			Model model) {
		List<SensorData> sensorDataList = new ArrayList<>();
		for (Long id : sensorDataIds) {
			Optional<SensorData> optionalSensorData = sensorDataRepository.findById(id);
			optionalSensorData.ifPresent(sensorDataList::add);
		}
		ModbusSlave modbusSlave = new ModbusSlave(nameModbusSlave, comPort, deviceAddress, sensorDataList, baudR,
				numDataBits, parity, comPortTimeouts);
		modbusSlaveRepository.save(modbusSlave);
		Iterable<ModbusSlave> modbusSlaves = modbusSlaveRepository.findAll();
		model.addAttribute("modbusSlaves", modbusSlaves);
		System.out.println("Вивід: " + modbusSlaves);
		return "modbus";
	}
}
