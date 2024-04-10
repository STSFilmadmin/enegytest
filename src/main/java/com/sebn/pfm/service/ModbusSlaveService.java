package com.sebn.pfm.service;


import com.sebn.pfm.modbus.ModbusSlave;
import com.sebn.pfm.repositories.ModbusSlaveRepository;

public class ModbusSlaveService {

    private final ModbusSlaveRepository modbusSlaveRepository;

    public ModbusSlaveService(ModbusSlaveRepository modbusSlaveRepository) {
        this.modbusSlaveRepository = modbusSlaveRepository;
    }

    public ModbusSlave getModbusSlaveById(Long id) {
        return modbusSlaveRepository.findById(id).orElse(null);
    }
}
