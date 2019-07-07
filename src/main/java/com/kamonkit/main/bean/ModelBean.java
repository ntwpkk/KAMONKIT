package com.kamonkit.main.bean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ModelBean {

	private long modelId;
	private String name;
	
	public ModelBean() {
		
	}
	
	public ModelBean(long modelId, String name) {
		super();
		this.modelId = modelId;
		this.name = name;
	}

	public ModelBean(String name) {
		super();
		this.name = name;
	}

	
}
