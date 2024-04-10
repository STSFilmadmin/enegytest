//package com.sebn.pfm.Energy.Controllers;
//
//import datamysql.Sensor;
//import repositories.SensorRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller // This means that this class is a Controller
//@RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)
//public class MainController {
//	 // This means to get the bean called userRepository Which is auto-generated by
//				// Spring, we will use it to handle the data
//	@Autowired
//	private SensorRepository sensorSlaveRepository;
//
//	@PostMapping(path = "/add") // Map ONLY POST Requests
//	public @ResponseBody String addSensorSlave(@RequestParam String name, @RequestParam String comport,
//			@RequestParam int setSlaveID, @RequestParam int dataAdress) {
//		// @ResponseBody means the returned String is the response, not a view name
//		// @RequestParam means it is a parameter from the GET or POST request
//
//		Sensor n = new Sensor();
//		n.setName(name);
//		n.setComport(comport);
//		n.setSlaveID(setSlaveID);
//		n.setDataAdress(dataAdress);
//		sensorSlaveRepository.save(n);
//		return "Saved";
//	}
//
//	@GetMapping(path = "/all")
//	public @ResponseBody Iterable<Sensor> getAllSensorSlave() {
//		// This returns a JSON or XML with the users
//		return sensorSlaveRepository.findAll();
//	}
//}