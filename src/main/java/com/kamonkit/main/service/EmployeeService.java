package com.kamonkit.main.service;

import java.util.List;

import com.kamonkit.main.bean.EmployeeBean;
import com.kamonkit.main.entity.Employee.Role;

public interface EmployeeService {

	EmployeeBean getEmployeeData(String fid);

	Role checkRoleUser(String fid);

	void insertEmployee(String createdBy, EmployeeBean eb);

	List<EmployeeBean> getAllEmployee();
}
