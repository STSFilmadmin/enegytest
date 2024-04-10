package com.sebn.pfm.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sebn.pfm.modbus.SensorData;
@Repository 
public interface SensorDataRepository extends CrudRepository<SensorData, Long>{
	 List<SensorData> findById(long id);
	 List<SensorData> findByDataName(String dataName);
	 

}
