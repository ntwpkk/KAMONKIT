package com.kamonkit.main.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@SequenceGenerator(name = "EMPLOYEE_SEQ")
@Entity(name = "EMPLOYEE")
@Table(indexes = {@Index(name = "EMPLOYEE_idx_employeeId_unique", columnList = "employeeId", unique = true)})
public class Employee extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 237168393772856398L;
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQ")
	private long employeeId;
	
	public Employee() {
		super();
	}
	
	public Employee(String fName, String lName, String telephone) {
		super();
		this.telephone = telephone;
		this.fName = fName;
		this.lName = lName;
	}

	@Enumerated(EnumType.ORDINAL)
	Role role;

    public enum Role {
        ADMINISTRATOR,
        ACCOUNTANT,
        INVESTIGATOR;
    }
	
}
