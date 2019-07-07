package com.kamonkit.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kamonkit.main.bean.MotorcycleBean;
import com.kamonkit.main.entity.Motorcycle.MotorcycleStatus;
import com.kamonkit.main.entity.Motorcycle.MotorcycleType;
import com.kamonkit.main.service.MotorcycleService;

@RestController
@RequestMapping("/motorcycle")
public class MotorcycleRestController {

	@Autowired MotorcycleService motorService;
	
	@PostMapping("/getDataStructure")
	public MotorcycleBean getDataStructure() {
		return new MotorcycleBean();
		
	}
	
	@PostMapping("/getMotorcycleByRegistNumber")
	public MotorcycleBean getMotorcycleByRegistNumber(@RequestParam(value="number")String number,@RequestParam(value="province")long province) {
		return motorService.getMotorcycleByRegistNumber(number.trim(),province);
		
	}
	
	@PostMapping("/getMotorcycleByEngineNumber")
	public MotorcycleBean getMotorcycleByEngineNumber(@RequestParam(value="number")String number) {
		return motorService.getMotorcycleByEngineNumber(number.trim());
		
	}
	
	@PostMapping("/getMotorcycleByChassisNumber")
	public MotorcycleBean getMotorcycleByChassisNumber(@RequestParam(value="number")String number) {
		return motorService.getMotorcycleByChassisNumber(number.trim());
		
	}
	
	@PostMapping("/insertMotorcycle")
	public void insertMotorcycleData(@RequestBody MotorcycleBean motorBean,@RequestParam(value="createdBy")String createdBy) {
		motorService.insertMotorcycle(motorBean, createdBy);
		
	}
	
	@PostMapping("/getAllMotorcycleStatus")
	public List<MotorcycleStatus> getAllMotorcycleStatus() {
		return motorService.getAllMotorcycleStatus();
	}
	
	@PostMapping("/getAllMotorcycleType")
	public List<MotorcycleType> getAllMotorcycleType() {
		return motorService.getAllMotorcycleType();
	}
	
	@PostMapping("/updateChassis")
	public void updateChassis(@RequestBody MotorcycleBean motorBean,String updatedBy) {
		motorService.updateChassis(motorBean, updatedBy);
		
	}
	
	@PostMapping("/updateEngine")
	public void updateEngine(@RequestBody MotorcycleBean motorBean,String updatedBy) {
		motorService.updateEngine(motorBean, updatedBy);
		
	}
	
	@PostMapping("/updateColor")
	public void updatecolor(@RequestBody MotorcycleBean motorBean,String updatedBy) {
		motorService.updatecolor(motorBean, updatedBy);
		
	}
	
	@PostMapping("/updateRegistration")
	public void updateRegistrationNumber(@RequestBody MotorcycleBean motorBean,String updatedBy) {
		motorService.updateRegistrationNumber(motorBean, updatedBy);
		
	}
	
	
	
	
}
