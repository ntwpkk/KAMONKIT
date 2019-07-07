package com.kamonkit.main.bean;



import com.kamonkit.main.entity.Employee;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class EmployeeBean {

	private long id;
	private String fName,lName,telephone, citizenId;
	private Employee.Role role;
	
	public EmployeeBean() {}
	
	public EmployeeBean(long id, String citizenId, String fName, String lName, String telephone, Employee.Role role) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.citizenId = citizenId;
		this.telephone =telephone;
		this.role = role;
	}

	public EmployeeBean(String fName, String lName) {
		super();
		this.fName = fName;
		this.lName = lName;
	}
	
	
	
	
}
