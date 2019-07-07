package com.kamonkit.main.bean;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class BrandBean {

	private long brandId;
	private String name;
	private List<ModelBean> model;
	
	public BrandBean() {
		
	}
	
	public BrandBean(long brandId, String name) {
		super();
		this.brandId = brandId;
		this.name = name;
	}

	public BrandBean(String name) {
		super();
		this.name = name;
	}

}
