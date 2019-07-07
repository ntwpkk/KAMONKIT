package com.kamonkit.main.service;

import java.util.List;
import java.util.Set;

import com.kamonkit.main.bean.ContractBean;
import com.kamonkit.main.bean.DateBean;
import com.kamonkit.main.bean.InstallmentBean;
import com.kamonkit.main.entity.Address;
import com.kamonkit.main.entity.Contract;

public interface ContractService {

	void insertContract(ContractBean contractBean, String user);

	ContractBean getContractData(DateBean date);
	
	ContractBean getContractByCode(String code);

	List<InstallmentBean> calculate(ContractBean cb);

	boolean checkHistory(Set<Contract> con);

	boolean checkLocation(Address a);

	boolean checkCredit(long id, double down, boolean gua);

	void payInstallment(ContractBean cb, String createdBy);

	ContractBean getContractDataByCode(String code);

	ContractBean getContractByCodeforPay(String code);

}
