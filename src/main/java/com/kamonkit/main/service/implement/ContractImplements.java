package com.kamonkit.main.service.implement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kamonkit.main.bean.ContractBean;
import com.kamonkit.main.bean.CustomerBean;
import com.kamonkit.main.bean.DateBean;
import com.kamonkit.main.bean.InstallmentBean;
import com.kamonkit.main.bean.MotorcycleBean;
import com.kamonkit.main.entity.Address;
import com.kamonkit.main.entity.Contract;
import com.kamonkit.main.entity.Contract.ContractStatus;
import com.kamonkit.main.entity.Customer;
import com.kamonkit.main.entity.Installment;
import com.kamonkit.main.entity.Motorcycle;
import com.kamonkit.main.repository.ColorRepos;
import com.kamonkit.main.repository.ContractRepos;
import com.kamonkit.main.repository.CustomerRepos;
import com.kamonkit.main.repository.InstallmentRepos;
import com.kamonkit.main.repository.ModelRepos;
import com.kamonkit.main.repository.MotorcycleRepos;
import com.kamonkit.main.repository.NameTitleRepos;
import com.kamonkit.main.repository.ProvinceRepos;
import com.kamonkit.main.repository.SubdistrictRepos;
import com.kamonkit.main.service.ContractService;

@Service
public class ContractImplements implements ContractService{

	@Autowired ContractRepos contractRp;
	@Autowired CustomerRepos custRp;
	@Autowired NameTitleRepos ntRp;
	@Autowired ProvinceRepos provRp;
	@Autowired SubdistrictRepos subRp;
	@Autowired ColorRepos colorRp;
	@Autowired MotorcycleRepos motorRp;
	@Autowired ModelRepos modelRp;
	@Autowired InstallmentRepos installRp;
	

	@Transactional
	@Override
	public ContractBean getContractData(DateBean date) {
		List<Contract> contract = contractRp.findAllByCreatedDate(date.getDate());
		System.out.println(contract.size());
		System.out.println(date.getDate());
		return null;
	}

	@Transactional
	@Override
	public void insertContract(ContractBean cb, String user) {
		Customer cust = custRp.findByCustomerIdAndIsDeletedFalse(cb.getCustomer().getCustomerId()),
				 gua = custRp.findByCustomerIdAndIsDeletedFalse(cb.getGuarantor().getCustomerId());
		Motorcycle motor = motorRp.findByMotorcycleIdAndIsDeletedFalse(cb.getMotorcycle().getMotorcycleId());
		Installment install = null;
		
		Contract con = new Contract(cb.getDownPayment(), cb.getInterestRate(), cb.getInterest(), cb.getCost(), cb.getCode(), cb.getNumberofInstallment(), cb.getInstallment().get(0).getPay());
		con.setStatus(Contract.ContractStatus.CONTRACT);
		con.setCreatedBy(user);
		
		con.setMotorcycle(motor);
		motor.getContract().add(con);
		
		con.setCustomer(cust);
		cust.getContract().add(con);
		
		con.setGuarantor(gua);
		gua.getContract().add(con);
		
//		int j=1;
//		for(InstallmentBean i : cb.getInstallment()) {
//			install = new Installment(j++);
//			install.setContract(con);
//			con.getInstallment().add(install);
//			install = null;
//		}
		
		contractRp.save(con);
	}

	@Transactional
	@Override
	public List<InstallmentBean> calculate(ContractBean cb) {
		List<InstallmentBean> ib = new ArrayList<InstallmentBean>();
		double cost, pay, interest, price;
		Calendar c = Calendar.getInstance();
	    c.set(Calendar.MONTH,new Date().getMonth());
	    c.set(Calendar.DAY_OF_MONTH, new Date().getDate());
		boolean gua = false;
		if(cb.getGuarantor()!=null)gua = true;
			
		if(checkCredit(cb.getCustomer().getCustomerId(), (double)cb.getDownPayment()*100.0/(double)cb.getMotorcycle().getPrice(), gua)  &&  checkCredit(cb.getGuarantor().getCustomerId(), (double)cb.getDownPayment()*100.0/(double)cb.getMotorcycle().getPrice(), gua) ) {
			price = (double)cb.getMotorcycle().getPrice() - (double)cb.getDownPayment();
			interest = price*(double)cb.getInterestRate()/100.0;
			cost = price / cb.getNumberofInstallment();
			pay =  Math.ceil(((double)cost + (double)interest)/10.0)*10;
			System.out.println(cb.getMotorcycle().getPrice()+" "+cb.getDownPayment()+" "+price+" "+cost+" "+pay+" "+interest+" "+cb.getInterestRate());
			for(int i = 1; i <= cb.getNumberofInstallment(); i++) {
				c.add(Calendar.MONTH, 1);
				ib.add(new InstallmentBean((int)cost, (int)interest, (int)pay, c.getTime().toGMTString().substring(0, 11)));
			}
		    
		}
		
		return ib;
	}
	
	@Transactional
	@Override
	public boolean checkCredit(long id,double down,boolean gua) {
		Customer c = custRp.findByCustomerIdAndIsDeletedFalse(id);
		boolean check =  false;
		if(c.getContract().size()<1) {
			
			if(checkLocation(c.getAddress())==true) {
				check = true;
			}
			
			else if(checkLocation(c.getAddress())==false) {
				
				if(down>=40.0) {
					check = true;
				}
				
				else if(20<down && down<40) {
					
					if(gua==true) {
						check = true;
					}
					
					else {
						check = false;
					}
				}
				else {
					check = false;
				}
			}
		}
		
		else{
			
			if(checkHistory(c.getContract()) == true) {
				check = true;
			}
			
			else {
				
				if(down>=40.0) {
					
					if(gua==true) {
						check = true;
					}
					
					else {
						check = false;
					}
				}
				
				else {
					check = false;
				}
			}
		}
		return check;
	}
	
	@Transactional
	@Override
	public boolean checkHistory(Set<Contract> con) {
		boolean check = true;
		for(Contract c : con) {
			if(c.getStatus().equals(Contract.ContractStatus.IMPOUND)) {
				check = false;
				break;
			}
		}
		return check;
	}
	
	@Transactional
	@Override
	public boolean checkLocation(Address a) {
		boolean check = false;
		String[] chon = {"บางนาง","บ้านเก่า","พานทอง","เกาะลอย"}, cha = {"บางผึ้ง","บางวัว","บางสมัคร","เขาดิน","ท่าสะอ้าน","ท่าข้าม","บางเกลือ","บางปะกง"};
		if(a.getSubdistrict().getDistrict().getProvince().getName().equals("ชลบุรี")) {	
			for(String c : chon) {
				if(a.getSubdistrict().getName().equals(c)) {
					check = true;
					break;
				}
			}
		}
		else if(a.getSubdistrict().getDistrict().getProvince().getName().equals("ฉะเชิงเทรา")) {	
			for(String c : cha) {
				if(a.getSubdistrict().getName().equals(c)) {
					check = true;
					break;
				}
			}
		}
		return check;
	}

	@Transactional
	@Override
	public ContractBean getContractByCode(String code) {
		Contract c = contractRp.findByCodeAndIsDeletedFalse(code);
		ContractBean cb = null;
			cb = new ContractBean(c.getContractId(),c.getMonthlyPay(),c.getNumberofInstallment(),c.getCode());
			cb.setCustomer(new CustomerBean(c.getCustomer().getFName(), c.getCustomer().getLName()));
			cb.setGuarantor(new CustomerBean(c.getGuarantor().getFName(), c.getGuarantor().getLName()));
			cb.setMotorcycle(new MotorcycleBean(c.getMotorcycle().getRegistrationNumber()+" "+c.getMotorcycle().getRegistrationProvince().getName()));
//			System.out.println("============================================================================================================");
//			for(Installment i : c.getInstallment()) {//installRp.findAllByContractOrderByNoAsc(c.getContractId())) {
//				if(i.getStatus()==Installment.InstallmentStatus.UNPAID || i.getStatus()==Installment.InstallmentStatus.LATEUNPAID) {
//					cb.setCurrentMonth(i.getNo());
//					System.out.println(i.getNo());
//					break;
//				}
//			}
			cb.setCurrentMonth(c.getInstallment().size()+1);
			
		
		return cb;
	}
	
	@Transactional
	@Override
	public ContractBean getContractByCodeforPay(String code) {
		Contract c = contractRp.findByCodeAndIsDeletedFalse(code);
		ContractBean cb = null;
			cb = new ContractBean(c.getContractId(),c.getMonthlyPay(),c.getNumberofInstallment(),c.getCode());
			cb.setCustomer(new CustomerBean(c.getCustomer().getFName(), c.getCustomer().getLName()));
			cb.setGuarantor(new CustomerBean(c.getGuarantor().getFName(), c.getGuarantor().getLName()));
			cb.setMotorcycle(new MotorcycleBean(c.getMotorcycle().getRegistrationNumber()+" "+c.getMotorcycle().getRegistrationProvince().getName()));
			
			long Allpay = c.getMonthlyPay()*c.getNumberofInstallment(),pay=0;
			for(Installment i : c.getInstallment()) {
				pay += (long)Math.ceil(((double)i.getCost()+(double)i.getInterest())/10.0)*10;
			}
			if(c.getInstallment().size()>0) {
				if(pay>=Allpay-(pay/c.getInstallment().size())) {
					cb.setCurrentMonth(100);
				}
				else {
					cb.setCurrentMonth(c.getInstallment().size()+1);
				}
			}
			else {
				cb.setCurrentMonth(c.getInstallment().size()+1);
			}
			
			
		
		return cb;
	}

	@Transactional
	@Override
	public void payInstallment(ContractBean cb, String createdBy) {
		Contract c = contractRp.findByContractIdAndIsDeletedFalse(cb.getContractId());
		c.setContractId(c.getContractId());
		long interest = (long) ((c.getMotorcycle().getPrice()-c.getDownPayment()) *(double)c.getInterestRate()/100.0);
		long cost = (c.getMotorcycle().getPrice()-c.getDownPayment()) / c.getNumberofInstallment();
		Installment in = new Installment();
		in.setCreatedBy(createdBy);
		in.setCost(cost);
		in.setInterest(interest);
		in.setNo(cb.getCurrentMonth());
		
		in.setContract(c);
		c.getInstallment().add(in);
		
		
		
		contractRp.save(c);
		
	}

	@Transactional
	@Override
	public ContractBean getContractDataByCode(String code) {
		Contract c = contractRp.findByCodeAndIsDeletedFalse(code);
		ContractBean cb = null;
		InstallmentBean ib;
		cb = new ContractBean(c.getContractId(),c.getMonthlyPay(),c.getNumberofInstallment(),c.getCode());
		cb.setNumberofInstallment(c.getNumberofInstallment());
		CustomerBean cu = new CustomerBean(),gu=new CustomerBean();
		cu.setFName(c.getCustomer().getFName()+" "+c.getCustomer().getLName());
		gu.setFName(c.getGuarantor().getFName()+" "+ c.getGuarantor().getLName());
		cb.setCustomer(cu);
		cb.setGuarantor(gu);
		MotorcycleBean mb =new MotorcycleBean();
		mb.setRegistrationNumber(c.getMotorcycle().getRegistrationNumber()+" "+c.getMotorcycle().getRegistrationProvince().getName());
		cb.setMotorcycle(mb);
//		System.out.println("============================================================================================================");
		for(Installment i : c.getInstallment()) {//installRp.findAllByContractOrderByNoAsc(c.getContractId())) {
			ib = new InstallmentBean();
			ib.setDateStr(i.getCreatedDate().toGMTString());
			ib.setPay(i.getCost()+i.getInterest());
			ib.setInstallmentId(i.getInstallmentId());
			ib.setNo(i.getNo());
			cb.getInstallment().add(ib);
			ib = null;
		}
		return cb;
	}
	
	


	
}
