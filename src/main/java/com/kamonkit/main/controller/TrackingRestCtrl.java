package com.kamonkit.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kamonkit.main.bean.EmployeeBean;
import com.kamonkit.main.entity.Employee;
import com.kamonkit.main.service.EmployeeService;


@RestController
@RequestMapping("/tracking")
public class TrackingRestCtrl {
	
	@Autowired  EmployeeService empService;

	@PostMapping("/getEmployeeData")
	public EmployeeBean getInfoPage(@RequestParam(value="fid") String fid) {
		System.out.println("emp");
		return empService.getEmployeeData(fid);
	}

	
	@PostMapping("/checkRoleUser")
	public Employee.Role checkRoleUser(@RequestParam(value="fid") String fid) {
		System.out.println("check emp");
		return empService.checkRoleUser(fid);
	}
	
	@PostMapping("/insertEmployee")
	public void insertEmployee(@RequestParam(value="createdBy") String createdBy, @RequestBody EmployeeBean eb) {
		 empService.insertEmployee(createdBy, eb);
	}
	
	@PostMapping("/getAllEmployee")
	public List<EmployeeBean> getAllEmployee(){
		return empService.getAllEmployee();
	}
	
	@PostMapping("/getAllEmployeeRole")
	public List<Employee.Role> getAllEmployeeRole(){
		List<Employee.Role> r = new ArrayList<Employee.Role>();
		r.add(Employee.Role.ACCOUNTANT);
		r.add(Employee.Role.INVESTIGATOR);
		return r;
	}
	
	
}
