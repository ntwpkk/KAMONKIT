package com.kamonkit.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kamonkit.main.bean.ContractBean;
import com.kamonkit.main.bean.InstallmentBean;
import com.kamonkit.main.bean.DateBean;
import com.kamonkit.main.service.ContractService;

@RestController
@RequestMapping("/contract")
public class ContractRestController {
	
	@Autowired  ContractService contractService;

	@PostMapping("/getDataStructure")
	public ContractBean getDataStructure() {
		return new ContractBean();
		
	} 
	
	@PostMapping("/insertContract")
	public void insertContract(@RequestParam(value="createdBy")String createdBy,@RequestBody ContractBean contractBean) {	
		contractService.insertContract(contractBean,createdBy);
	}
	
	@PostMapping("/getContractData")
	public ContractBean getContractData(@RequestBody DateBean date) {
		return contractService.getContractData(date);
	}
	
	@PostMapping("/calculate")
	public List<InstallmentBean> calculate(@RequestBody ContractBean cb) {
		return contractService.calculate(cb);
	}
	
	@PostMapping("/getContractByCode")
	public ContractBean getContractByCode(String code) {
		return contractService.getContractByCode(code.trim());
	}
	
	@PostMapping("/getContractDataByCode")
	public ContractBean getContractDataByCode(String code) {
		return contractService.getContractDataByCode(code.trim());
	}
	
	@PostMapping("/payInstallment")
	public void payInstallment(String createdBy,@RequestBody ContractBean cb) {	
		contractService.payInstallment(cb,createdBy);
	}
	
	@PostMapping("/getContractByCodeforPay")
	public ContractBean getContractByCodeforPay(String code) {
		return contractService.getContractByCodeforPay(code.trim());
	}

}
