package com.sebn.pfm.modbus;


import com.sebn.pfm.rs485.enums.DataType;
import com.sebn.pfm.rs485.enums.Endianness;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
/**
 * Представляє дані датчика, які використовуються в системі збору даних Modbus.
 * Клас містить інформацію про ім'я даних, адресу датчика, тип даних, порядок байтів,
 * коефіцієнт конвертації та інші характеристики.
 */
@Entity // This tells Hibernate to make a table out of this class
public class SensorData {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Використовуємо автоінкремент в базі даних
    private long id;
  // @Column
    private String dataName; // Ім'я даних
  // @Column
    private int address; // Адреса даних
  // @Column
    private int numberOfRegisters;// кількусть регістріві для даних 
  // @Column
    private int command;    
  // @Column
    private DataType dataType; // Тип даних
  // @Column
    private Endianness endianness; // Порядок байтів
  // @Column
    private float coefficient; // Коефіцієнт
   //@Column
    private boolean saveToDatabase; // Поле, що вказує, чи потрібно зберігати ці дані в базі даних
    @ManyToOne // Зв'язок багато SensorData до одного ModbusSlave
    private ModbusSlave modbusSlave;
    
    
   
    public ModbusSlave getModbusSlave() {
		return modbusSlave;
	}

	public void setModbusSlave(ModbusSlave modbusSlave) {
		this.modbusSlave = modbusSlave;
	}

	/**
     * Конструктор для створення об'єкту SensorData.
     *
     * @param dataName         Ім'я даних (String).
     * @param address          Адреса (int).
     * @param numberOfRegisters Кількість регістрів для адреси (int).
     * @param command          Команда (int).	1 (0x01)- читання значень з декількох регістрів прапорів(Read Coil Status)
     *							2 (0x02)- читання значень з декількох дискретних входів(Read Discrete Inputs)
     *							3 (0x03)- читання значень з декількох регістрів зберігання(Read Holding Registers)
     *							4 (0x04)- читання значень з декількох регістрів введення(Read Input Registers)
     * @param dataType         Тип даних (DataType).
     * @param endianness       Порядок байтів (Endianness).
     * @param coefficient      Коефіцієнт (float).
     * @param saveToDatabase   Поле, що вказує, чи потрібно зберігати ці дані в базі даних (boolean).
     */
	public SensorData(String dataName, int address, int numberOfRegisters, int command, DataType dataType, Endianness endianness,
		float coefficient, boolean saveToDatabase) {
		
		this.dataName = dataName;
		this.address = address;
		this.numberOfRegisters = numberOfRegisters;
		this.command = command;
		this.dataType = dataType;
		this.endianness = endianness;
		this.coefficient = coefficient;
		this.saveToDatabase = saveToDatabase;
	}
	 
    public SensorData() {
	}
    
    //гетери і сетери
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address; 
	}
	public int getNumberOfRegisters() {
		return numberOfRegisters;
	}
	public void setNumberOfRegisters(int numberOfRegisters) {
		this.numberOfRegisters = numberOfRegisters;
	}
	public DataType getDataType() {
		return dataType;
	}
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	public Endianness getEndianness() {
		return endianness;
	}
	public void setEndianness(Endianness endianness) {
		this.endianness = endianness;
	}
	public float getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(float coefficient) {
		this.coefficient = coefficient;
	}
	public boolean isSaveToDatabase() {
		return saveToDatabase;
	}
	public void setSaveToDatabase(boolean saveToDatabase) {
		this.saveToDatabase = saveToDatabase;
	}
	public int getCommand() {
		return command;
	}
	public void setCommand(int command) {
		this.command = command;
	}
	
	
    
}