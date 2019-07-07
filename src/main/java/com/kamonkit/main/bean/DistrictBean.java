package com.kamonkit.main.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class DistrictBean {

	private long districtId;
	private String name;
	private List<SubdistrictBean> subdistrict = new ArrayList<SubdistrictBean>();
	
	public DistrictBean() {
		
	}
	
	public DistrictBean(long districtId, String name) {
		super();
		this.districtId = districtId;
		this.name = name;
	}

	public DistrictBean(String name) {
		super();
		this.name = name;
	}

	
	

	

	

	
}
