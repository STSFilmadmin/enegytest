package com.sebn.pfm.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sebn.pfm.modbus.ModbusSlave;

public interface ModbusSlaveRepository extends CrudRepository<ModbusSlave, Long> {
    List<ModbusSlave> findByNameModbusSlave(String name); // Метод для пошуку за іменем
    List<ModbusSlave> findByComPort(String comPort); // Метод для пошуку за компортом
    List<ModbusSlave> findByid(Long id);
    // Метод для пошуку за ID
}
