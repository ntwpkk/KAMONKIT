package com.kamonkit.main.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kamonkit.main.entity.Motorcycle;
import com.kamonkit.main.entity.Motorcycle.MotorcycleStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class MotorcycleBean {

	private long motorcycleId;
	private Motorcycle.MotorcycleStatus status;
	private Motorcycle.MotorcycleType type;
	private String registrationNumber;
	private ProvinceBean registrationProvince;
	private String chassisNumber;
	private String engineNumber;
	private ModelBean model;
	private BrandBean brand;
	private ColorBean color;
    private List<ContractBean> contractBean = new ArrayList<ContractBean>();
    private Date date;
    private String note;
    private long price,downPayment;
    
	public MotorcycleBean() {
		super();
	}
	
	public MotorcycleBean(long motorcycleId, MotorcycleStatus status, String registrationNumber, String chassisNumber,
			String engineNumber) {
		super();
		this.motorcycleId = motorcycleId;
		this.status = status;
		this.registrationNumber = registrationNumber;
		this.chassisNumber = chassisNumber;
		this.engineNumber = engineNumber;
	}

	public MotorcycleBean(String registrationNumber) {
		super();
		this.registrationNumber = registrationNumber;
	}
	
    
	
    
}
