package com.kamonkit.main.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kamonkit.main.bean.CustomerBean;
import com.kamonkit.main.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {
	
	@Autowired CustomerService custService;
	
	@PostMapping("/getDataStructure")
	public CustomerBean getCustomer() {
		
		return new CustomerBean();
	}
	
	@PostMapping("/getCustomerName")
	public CustomerBean getCustomerName(@RequestParam(value="fid")String fid) {
		
		return custService.getCustomerName(fid);
	}

	@PostMapping("/getCustomerByCitizenId")
	public CustomerBean getCustomerByCitizenId(@RequestParam(value="citizenId")String citizenId) {
		
		return custService.getCustomerByCitizenId(citizenId.trim());
	}
	
	@PostMapping("/getCustomerDataByCitizenId")
	public CustomerBean getCustomerDataByCitizenId(@RequestParam(value="citizenId")String citizenId) {
		
		return custService.getCustomerDataByCitizenId(citizenId.trim());
	}
	
	@PostMapping("/getCustomerDataByFirebaseId")
	public CustomerBean getCustomerDataByFirebaseId(@RequestParam(value="fid")String fid) {
		
		return custService.getCustomerDataByFirebaseId(fid);
	}
	
	@PostMapping("/getCustomerByName")
	public CustomerBean getCustomerByName(@RequestParam(value="fname")String fname, @RequestParam(value="lname")String lname) {
		
		return custService.getCustomerByName(fname.trim(), lname.trim());
	}
	
	@PostMapping("/getCustomerDataByName")
	public CustomerBean getCustomerDataByName(@RequestParam(value="fname")String fname, @RequestParam(value="lname")String lname) {
		
		return custService.getCustomerDataByName(fname.trim(), lname.trim());
	}
	
	@PostMapping("/test")
	public void test(@RequestBody Date date) {
		
		System.out.println(date);
	}
	
	@PostMapping("/insertCustomer")
	public void insertCustomer(@RequestParam(value="createdBy")String createdBy,@RequestBody CustomerBean cb) {
		
		custService.insertCustomer(createdBy,cb);
	}
	
	@PostMapping("/updateAddress")
	public void updateAddress(@RequestParam(value="updatedBy")String updatedBy,@RequestBody CustomerBean cb) {
		
		custService.updateAddress(updatedBy,cb);
	}
	
	@PostMapping("/updateName")
	public void updateName(String updatedBy,@RequestBody CustomerBean cb) {
		
		custService.updateName(updatedBy,cb);
	}
	
	@PostMapping("/updateTelephone")
	public void updateTelephone(String updatedBy,@RequestBody CustomerBean cb) {
		
		custService.updateTelephone(updatedBy,cb);
	}
}
