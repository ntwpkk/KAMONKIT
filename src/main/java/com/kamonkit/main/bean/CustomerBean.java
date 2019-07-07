package com.kamonkit.main.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class CustomerBean {

	private long customerId;
	private AddressBean address;
	private AddressBean workplace;
    private List<ContractBean> contract = new ArrayList<ContractBean>();
    private String telephone;
    private Date birthday;
    private String fName,lName;
    private NameTitleBean nameTitle;
	private String citizenId,bdD,bdM,bdY;
	
	public CustomerBean() {}


	public CustomerBean(long customerId, String citizenId, String fName, String lName, String telephone, String bdD, String bdM, String bdY) {
		super();
		this.customerId = customerId;
		this.telephone = telephone;
		this.bdD = bdD;
		this.bdM = bdM;
		this.bdY = bdY;
		this.fName = fName;
		this.lName = lName;
		this.citizenId = citizenId;
	}


	public CustomerBean(String fName, String lName) {
		super();
		this.fName = fName;
		this.lName = lName;
	}
	
	

	
    
	
}
