package com.kamonkit.main.bean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class NameTitleBean {
	
	private long nameTitleId;
	private String name;
	
	public NameTitleBean() {
		super();
	}
	public NameTitleBean(long nameTitleId, String name) {
		super();
		this.nameTitleId = nameTitleId;
		this.name = name;
	}

}
