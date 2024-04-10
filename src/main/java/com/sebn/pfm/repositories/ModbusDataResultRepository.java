package com.sebn.pfm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sebn.pfm.modbus.ModbusDataResult;

public interface ModbusDataResultRepository extends CrudRepository<ModbusDataResult, Long>{
	 List<ModbusDataResult> findByModbusSlaveName(String name);
	    List<ModbusDataResult> findBySensorName(String name);
	    Optional<ModbusDataResult> findById(Long id);

}


