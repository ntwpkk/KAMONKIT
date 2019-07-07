package com.kamonkit.main.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ProvinceBean {

	private long provinceId;
	private String name;
	private List<DistrictBean> district = new ArrayList<DistrictBean>();
	
	public ProvinceBean() {
		
	}
	
	public ProvinceBean(long provinceId, String name) {
		super();
		this.provinceId = provinceId;
		this.name = name;
	}

	public ProvinceBean(String name) {
		super();
		this.name = name;
	}

	
	
}
