package com.sebn.pfm.modbus;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
/**
 * Клас, який представляє пристрій ModbusSlave.
 * 
 * Цей клас містить інформацію про пристрій ModbusSlave, таку як назва, послідовний порт,
 * адреса пристрою, налаштування зв'язку, список даних сенсорів та інші параметри.
 * 
 * Цей клас використовується для взаємодії з пристроями ModbusSlave та зберігання
 * їхньої конфігурації в базі даних.
 */
@Entity // This tells Hibernate to make a table out of this class
public class ModbusSlave {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;
    private String nameModbusSlave;
    private String comPort;
	private int deviceAddress;
	@ManyToMany// @OneToMany(mappedBy = "SensorData")//@ManyToMany//@ElementCollection
    private List<SensorData> sensorDataList; // Поле для списку об'єктів SensorData
    private int baudR;
    private int numDataBits;
    private int parity;
    private boolean comPortTimeouts;
    
    
    /**
     * Конструктор для створення об'єкту ModbusSlave.
     *
     * @param nameModbusSlave   Назва пристрою (String).
     * @param comPort           Послідовний порт (String).
     * @param deviceAddress     Адреса пристрою (int).
     * @param sensorDataList    Список даних датчиків (List<SensorData>).
     *	*	Конструктор для створення об'єкту SensorData.
     *  *	@param dataName         Ім'я даних (String).
     *	*	@param address          Адреса (int).
     * 	*	@param numberOfRegisters Кількість регістрів для адреси (int).
     * 	*	@param command          Команда (int).
     * 	*	@param dataType         Тип даних (DataType).
     * 	*	@param endianness       Порядок байтів (Endianness).
     * 	*	@param coefficient      Коефіцієнт (float).
     * 	*	@param saveToDatabase   Поле, що вказує, чи потрібно зберігати ці дані в базі даних (boolean).
     *  *	@param baudR             Швидкість передачі даних (int).
     * 	*	@param numDataBits       Кількість бітів даних (int).
     * 	*	@param parity            Паритет (int).
     * 	*	@param comPortTimeouts   Таймаути порту (boolean).
     */
	public ModbusSlave(String nameModbusSlave, String comPort, int deviceAddress, List<SensorData> sensorDataList,
			int baudR, int numDataBits, int parity, boolean comPortTimeouts) {
		super();
		this.nameModbusSlave = nameModbusSlave;
		this.comPort = comPort;
		this.deviceAddress = deviceAddress;
		this.sensorDataList = sensorDataList;
		this.baudR = baudR;
		this.numDataBits = numDataBits;
		this.parity = parity;
		this.comPortTimeouts = comPortTimeouts;
	}


	public ModbusSlave() {
	}
    // геттери та сеттери


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNameModbusSlave() {
		return nameModbusSlave;
	}


	public void setNameModbusSlave(String nameModbusSlave) {
		this.nameModbusSlave = nameModbusSlave;
	}


	public String getComPort() {
		return comPort;
	}


	public void setComPort(String comPort) {
		this.comPort = comPort;
	}


	public int getDeviceAddress() {
		return deviceAddress;
	}


	public void setDeviceAddress(int deviceAddress) {
		this.deviceAddress = deviceAddress;
	}


	public List<SensorData> getSensorDataList() {
		return sensorDataList;
	}


	public void setSensorDataList(List<SensorData> sensorDataList) {
		this.sensorDataList = sensorDataList;
	}


	public int getBaudR() {
		return baudR;
	}


	public void setBaudR(int baudR) {
		this.baudR = baudR;
	}


	public int getNumDataBits() {
		return numDataBits;
	}


	public void setNumDataBits(int numDataBits) {
		this.numDataBits = numDataBits;
	}


	public int getParity() {
		return parity;
	}


	public void setParity(int parity) {
		this.parity = parity;
	}


	public boolean isComPortTimeouts() {
		return comPortTimeouts;
	}


	public void setComPortTimeouts(boolean comPortTimeouts) {
		this.comPortTimeouts = comPortTimeouts;
	}

}
