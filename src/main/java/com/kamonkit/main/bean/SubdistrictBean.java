package com.kamonkit.main.bean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class SubdistrictBean {

	private long subdistrictId;
	private String name;
	private long zipcode;
	
	public SubdistrictBean() {
		
	}
	
	public SubdistrictBean(long subdistrictId, String name) {
		super();
		this.subdistrictId = subdistrictId;
		this.name = name;
	}

	public SubdistrictBean(String name) {
		super();
		this.name = name;
	}

	public SubdistrictBean(long subdistrictId, String name, long zipcode) {
		super();
		this.subdistrictId = subdistrictId;
		this.name = name;
		this.zipcode = zipcode;
	}

}
