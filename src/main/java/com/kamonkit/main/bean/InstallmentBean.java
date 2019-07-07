package com.kamonkit.main.bean;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class InstallmentBean {
	private long installmentId;
	private long cost,interest,pay,no;
	private Date date;
	private String dateStr;
	
	
	
	public InstallmentBean(long cost, long interest, long price, String date) {
		super();
		this.cost = cost;
		this.interest = interest;
		this.pay = price;
		this.dateStr = date;
	}


	public InstallmentBean() {
		super();
	}
	
	
}
