package com.kamonkit.main.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.MappedSuperclass;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@MappedSuperclass
public class User extends BaseDomain{

	
	/**
	 * 
	 */
	protected static final long serialVersionUID = 8771847059688468249L;
	protected String fName,lName,firebaseId,citizenId;
	protected Date birthDay;
	protected String telephone;
	
	
}
