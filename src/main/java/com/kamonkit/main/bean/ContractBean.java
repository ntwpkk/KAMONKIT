package com.kamonkit.main.bean;

import java.util.ArrayList;
import java.util.List;

import com.kamonkit.main.entity.Contract;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ContractBean {

	private long contractId;
	private long downPayment,interest,cost,monthlyPay,numberofInstallment;
	private double interestRate;
	private String code, customerStatus;
	private CustomerBean customer;
	private CustomerBean guarantor;
	private MotorcycleBean motorcycle;
	private EmployeeBean employee;
	private List<InstallmentBean> installment = new ArrayList<InstallmentBean>();
	private Contract.ContractStatus status;
	private long currentMonth;
	
	
	public ContractBean() {
		
	}


	public ContractBean(long contractId, long monthlyPay, long numberofInstallment, String code) {
		super();
		this.contractId = contractId;
		this.monthlyPay = monthlyPay;
		this.numberofInstallment = numberofInstallment;
		this.code = code;
	}
	
}
