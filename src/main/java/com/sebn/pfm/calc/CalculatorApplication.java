//package com.sebn.pfm.calc;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
//import jakarta.persistence.*;
//
//@SpringBootApplication
//public class CalculatorApplication {
//
//    public static void main(String[] args) {
//        ApplicationContext context = SpringApplication.run(CalculatorApplication.class, args);
//        CalculatorService calculatorService = context.getBean(CalculatorService.class);
//        
//        int a = 55;
//        int b = 35;
//        int c = calculatorService.add(a, b);
//        
//        //String test= ModbusRTUReader.NewModbus();
//        //System.out.println(test);
//    }
//}
//
//@Component
//class CalculatorService {
//    private final AdditionResultRepository additionResultRepository;
//
//    @Autowired
//    public CalculatorService(AdditionResultRepository additionResultRepository) {
//        this.additionResultRepository = additionResultRepository;
//    }
//
//    public int add(int operand1, int operand2) {
//        int result = operand1 + operand2;
//        
//        // Збереження результату в базі даних
//        AdditionResult additionResult = new AdditionResult();
//        additionResult.setOperand1(operand1);
//        additionResult.setOperand2(operand2);
//        additionResult.setResult(result);
//        additionResultRepository.save(additionResult);
//        
//        return result;
//    }
//}
//
//@Entity
//class AdditionResult {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    
//    private int operand1;
//    private int operand2;
//    private int result;
//
//    // Getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public int getOperand1() {
//        return operand1;
//    }
//
//    public void setOperand1(int operand1) {
//        this.operand1 = operand1;
//    }
//
//    public int getOperand2() {
//        return operand2;
//    }
//
//    public void setOperand2(int operand2) {
//        this.operand2 = operand2;
//    }
//
//    public int getResult() {
//        return result;
//    }
//
//    public void setResult(int result) {
//        this.result = result;
//    }
//}
//
//interface AdditionResultRepository extends CrudRepository<AdditionResult, Long> {
//}
