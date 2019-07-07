package com.kamonkit.main.bean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ColorBean {

	private long colorId;
	private String name;
	
	public ColorBean() {
		
	}
	
	public ColorBean(long colorId, String name) {
		super();
		this.colorId = colorId;
		this.name = name;
	}

	public ColorBean(String name) {
		super();
		this.name = name;
	}

}
