package com.kamonkit.main.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kamonkit.main.bean.AddressBean;
import com.kamonkit.main.bean.ContractBean;
import com.kamonkit.main.bean.CustomerBean;
import com.kamonkit.main.bean.DistrictBean;
import com.kamonkit.main.bean.MotorcycleBean;
import com.kamonkit.main.bean.NameTitleBean;
import com.kamonkit.main.bean.ProvinceBean;
import com.kamonkit.main.bean.SubdistrictBean;
import com.kamonkit.main.entity.Address;
import com.kamonkit.main.entity.Contract;
import com.kamonkit.main.entity.Customer;
import com.kamonkit.main.entity.Employee;
import com.kamonkit.main.entity.NameTitle;
import com.kamonkit.main.entity.Subdistrict;
import com.kamonkit.main.repository.CustomerRepos;
import com.kamonkit.main.repository.EmployeeRepos;
import com.kamonkit.main.repository.NameTitleRepos;
import com.kamonkit.main.repository.SubdistrictRepos;
import com.kamonkit.main.service.CustomerService;

@Service
public class CustomerImplements implements CustomerService{
	
	@Autowired CustomerRepos customerRp;
	@Autowired EmployeeRepos empRp;
	@Autowired NameTitleRepos ntRp;
	@Autowired SubdistrictRepos sdRp;
	@Autowired UserImplements userService;

	@Transactional
	@Override
	public CustomerBean getCustomerByCitizenId(String citizenId) {
		String d = null,m = null,y = null;
		Customer c = customerRp.findByCitizenIdAndIsDeletedFalse(citizenId);
		CustomerBean cb = null;
//		if(c!=null) {
			if(c.getBirthDay()!=null) {
				d = c.getBirthDay().toString().substring(8, 10);
				m = c.getBirthDay().toString().substring(5, 7);
				y = c.getBirthDay().toString().substring(0, 4);
			}
			cb = new CustomerBean(c.getCustomerId(),c.getCitizenId(),c.getFName(),c.getLName(),c.getTelephone(),d,m,y);
			if(c.getAddress()!=null && c.getAddress().getSubdistrict()!=null)
			cb.setAddress(new AddressBean(c.getAddress().getAddressId(),c.getAddress().getDetail(),new ProvinceBean(c.getAddress().getSubdistrict().getDistrict().getProvince().getProvinceId(),c.getAddress().getSubdistrict().getDistrict().getProvince().getName()),new DistrictBean(c.getAddress().getSubdistrict().getDistrict().getDistrictId(),c.getAddress().getSubdistrict().getDistrict().getName()),new SubdistrictBean(c.getAddress().getSubdistrict().getSubdistrictId(),c.getAddress().getSubdistrict().getName()),c.getAddress().getSubdistrict().getZipcode()));
			if(c.getWorkplace()!=null && c.getWorkplace().getSubdistrict()!=null)
			cb.setWorkplace(new AddressBean(c.getWorkplace().getAddressId(),c.getWorkplace().getDetail(),new ProvinceBean(c.getWorkplace().getSubdistrict().getDistrict().getProvince().getProvinceId(),c.getWorkplace().getSubdistrict().getDistrict().getProvince().getName()),new DistrictBean(c.getWorkplace().getSubdistrict().getDistrict().getDistrictId(),c.getWorkplace().getSubdistrict().getDistrict().getName()),new SubdistrictBean(c.getWorkplace().getSubdistrict().getSubdistrictId(),c.getWorkplace().getSubdistrict().getName()),c.getWorkplace().getSubdistrict().getZipcode()));
			if(c.getNameTitle()!=null)
			cb.setNameTitle(new NameTitleBean(c.getNameTitle().getNameTitleId(),c.getNameTitle().getName()));
//		}
		System.out.println(citizenId);
		return cb;
	}

	@Transactional
	@Override
	public void insertCustomer(String createdBy, CustomerBean cb) {
		Employee emp = empRp.findByFirebaseIdAndIsDeletedFalse(createdBy);
		if((emp!=null) && emp.getRole().equals(Employee.Role.ACCOUNTANT) || emp.getRole().equals(Employee.Role.ADMINISTRATOR)) {
			Customer cu = new Customer(cb.getCitizenId(),cb.getFName(),cb.getLName(),cb.getTelephone(),cb.getBirthday());
			Address ad = new Address(),wp = new Address();
			NameTitle nt = ntRp.findByNameTitleIdAndIsDeletedFalse(cb.getNameTitle().getNameTitleId());
			Subdistrict sda = sdRp.findBySubdistrictIdAndIsDeletedFalse(cb.getAddress().getSubdistrict().getSubdistrictId()),
					sdw = sdRp.findBySubdistrictIdAndIsDeletedFalse(cb.getWorkplace().getSubdistrict().getSubdistrictId());
			
			ad.setDetail(cb.getAddress().getDetail());
			ad.setSubdistrict(sda);
			wp.setDetail(cb.getWorkplace().getDetail());
			wp.setSubdistrict(sdw);
			
			cu.setAddress(ad);
			cu.setWorkplace(wp);
			
			cu.setNameTitle(nt);
			nt.getCustomer().add(cu);
			
			cu.setFirebaseId(userService.createUser(cu.getCitizenId()+"@kamonkit.com"));
			
			customerRp.save(cu);
		}
		
	}

	@Transactional
	@Override
	public CustomerBean getCustomerByName(String fname, String lname) {
		String d = null,m = null,y = null;
		Customer c = customerRp.findByFNameAndLNameAndIsDeletedFalse(fname, lname);
		CustomerBean cb = null;
//		if(c!=null) {
			if(c.getBirthDay()!=null) {
				d = c.getBirthDay().toString().substring(8, 10);
				m = c.getBirthDay().toString().substring(5, 7);
				y = c.getBirthDay().toString().substring(0, 4);
			}
			cb = new CustomerBean(c.getCustomerId(),c.getCitizenId(),c.getFName(),c.getLName(),c.getTelephone(),d,m,y);
			if(c.getAddress()!=null && c.getAddress().getSubdistrict()!=null)
			cb.setAddress(new AddressBean(c.getAddress().getAddressId(),c.getAddress().getDetail(),new ProvinceBean(c.getAddress().getSubdistrict().getDistrict().getProvince().getProvinceId(),c.getAddress().getSubdistrict().getDistrict().getProvince().getName()),new DistrictBean(c.getAddress().getSubdistrict().getDistrict().getDistrictId(),c.getAddress().getSubdistrict().getDistrict().getName()),new SubdistrictBean(c.getAddress().getSubdistrict().getSubdistrictId(),c.getAddress().getSubdistrict().getName()),c.getAddress().getSubdistrict().getZipcode()));
			if(c.getWorkplace()!=null && c.getWorkplace().getSubdistrict()!=null)
			cb.setWorkplace(new AddressBean(c.getWorkplace().getAddressId(),c.getWorkplace().getDetail(),new ProvinceBean(c.getWorkplace().getSubdistrict().getDistrict().getProvince().getProvinceId(),c.getWorkplace().getSubdistrict().getDistrict().getProvince().getName()),new DistrictBean(c.getWorkplace().getSubdistrict().getDistrict().getDistrictId(),c.getWorkplace().getSubdistrict().getDistrict().getName()),new SubdistrictBean(c.getWorkplace().getSubdistrict().getSubdistrictId(),c.getWorkplace().getSubdistrict().getName()),c.getWorkplace().getSubdistrict().getZipcode()));
			if(c.getNameTitle()!=null)
			cb.setNameTitle(new NameTitleBean(c.getNameTitle().getNameTitleId(),c.getNameTitle().getName()));
//		}
		return cb;
	}

	@Transactional
	@Override
	public void updateAddress(String updatedBy, CustomerBean cb) {
		Customer c = customerRp.findByCustomerIdAndIsDeletedFalse(cb.getCustomerId());
		Subdistrict sa, sw;
		Address a, w;
		if(c!=null) {
			c.setCustomerId(c.getCustomerId());
			c.setUpdatedBy(updatedBy);
			
			a = c.getAddress();
			a.setDetail(cb.getAddress().getDetail());
			sa = sdRp.findBySubdistrictIdAndIsDeletedFalse(cb.getAddress().getSubdistrict().getSubdistrictId());
			a.setSubdistrict(sa);
			sa.getAddress().add(a);
			c.setAddress(a);
			
			w = c.getWorkplace();
			w.setDetail(cb.getWorkplace().getDetail());
			sw = sdRp.findBySubdistrictIdAndIsDeletedFalse(cb.getWorkplace().getSubdistrict().getSubdistrictId());
			w.setSubdistrict(sw);
			sw.getAddress().add(w);
			c.setWorkplace(w);
			
			customerRp.save(c);
		}
		
		
	}

	
	@Transactional
	@Override
	public void updateName(String updatedBy, CustomerBean cb) {
		Customer c = customerRp.findByCustomerIdAndIsDeletedFalse(cb.getCustomerId());
		if(c!=null) {
			c.setUpdatedBy(updatedBy);
			c.setCustomerId(c.getCustomerId());
			c.setFName(cb.getFName());
			c.setLName(cb.getLName());
			customerRp.save(c);
		}
	}

	@Transactional
	@Override
	public void updateTelephone(String updatedBy, CustomerBean cb) {
		Customer c = customerRp.findByCustomerIdAndIsDeletedFalse(cb.getCustomerId());
		if(c!=null) {
			c.setUpdatedBy(updatedBy);
			c.setCustomerId(c.getCustomerId());
			c.setTelephone(cb.getTelephone());
			customerRp.save(c);
		}
		
	}

	@Transactional
	@Override
	public CustomerBean getCustomerDataByCitizenId(String id) {
		String d = null,m = null,y = null;
		Customer c = customerRp.findByCitizenIdAndIsDeletedFalse(id);
		CustomerBean cb = null;
		AddressBean ab = null, wb = null;
		ContractBean conb = null;
			if(c.getBirthDay()!=null) {
				d = c.getBirthDay().toString().substring(8, 10);
				m = c.getBirthDay().toString().substring(5, 7);
				y = c.getBirthDay().toString().substring(0, 4);
			}
			cb = new CustomerBean(c.getCustomerId(),c.getCitizenId(),c.getFName(),c.getLName(),c.getTelephone(),d,m,y);
			if(c.getAddress()!=null && c.getAddress().getSubdistrict()!=null) {
				ab = new AddressBean();
				ab.setDetail(c.getAddress().getDetail()+ " " + c.getAddress().getSubdistrict().getName()+" "+c.getAddress().getSubdistrict().getDistrict().getName()+" จังหวัด"+c.getAddress().getSubdistrict().getDistrict().getProvince().getName()+" "+c.getAddress().getSubdistrict().getZipcode());
				cb.setAddress(ab);
			}
			if(c.getWorkplace()!=null && c.getWorkplace().getSubdistrict()!=null) {
				wb = new AddressBean();
				wb.setDetail(c.getWorkplace().getDetail()+ " " + c.getWorkplace().getSubdistrict().getName()+" "+c.getWorkplace().getSubdistrict().getDistrict().getName()+" จังหวัด"+c.getWorkplace().getSubdistrict().getDistrict().getProvince().getName()+" "+c.getWorkplace().getSubdistrict().getZipcode());
				cb.setWorkplace(wb);
			}
			if(c.getNameTitle()!=null)
			cb.setNameTitle(new NameTitleBean(c.getNameTitle().getNameTitleId(),c.getNameTitle().getName()));
			
			
			
			for(Contract con : c.getContract()) {
				conb = new ContractBean();
				conb.setMotorcycle(new MotorcycleBean(con.getMotorcycle().getRegistrationNumber()+" "+con.getMotorcycle().getRegistrationProvince().getName()));
				conb.setCode(con.getCode());
				conb.setStatus(con.getStatus());
				if(con.getCustomer().getCustomerId() == c.getCustomerId()) {
					conb.setCustomerStatus("Customer");
				}
				else if(con.getGuarantor().getCustomerId() == c.getCustomerId()){
					conb.setCustomerStatus("Guarantor");
				}
				cb.getContract().add(conb);
			}
			
		return cb;
	}

	@Transactional
	@Override
	public CustomerBean getCustomerDataByName(String fname, String lname) {
		String d = null,m = null,y = null;
		Customer c = customerRp.findByFNameAndLNameAndIsDeletedFalse(fname, lname);
		CustomerBean cb = null;
		AddressBean ab = null, wb = null;
		ContractBean conb = null;
			if(c.getBirthDay()!=null) {
				d = c.getBirthDay().toString().substring(8, 10);
				m = c.getBirthDay().toString().substring(5, 7);
				y = c.getBirthDay().toString().substring(0, 4);
			}
			cb = new CustomerBean(c.getCustomerId(),c.getCitizenId(),c.getFName(),c.getLName(),c.getTelephone(),d,m,y);
			if(c.getAddress()!=null && c.getAddress().getSubdistrict()!=null) {
				ab = new AddressBean();
				ab.setDetail(c.getAddress().getDetail()+ " " + c.getAddress().getSubdistrict().getName()+" "+c.getAddress().getSubdistrict().getDistrict().getName()+" จังหวัด"+c.getAddress().getSubdistrict().getDistrict().getProvince().getName()+" "+c.getAddress().getSubdistrict().getZipcode());
				cb.setAddress(ab);
			}
			if(c.getWorkplace()!=null && c.getWorkplace().getSubdistrict()!=null) {
				wb = new AddressBean();
				wb.setDetail(c.getWorkplace().getDetail()+ " " + c.getWorkplace().getSubdistrict().getName()+" "+c.getWorkplace().getSubdistrict().getDistrict().getName()+" จังหวัด"+c.getWorkplace().getSubdistrict().getDistrict().getProvince().getName()+" "+c.getWorkplace().getSubdistrict().getZipcode());
				cb.setWorkplace(wb);
			}
			if(c.getNameTitle()!=null)
			cb.setNameTitle(new NameTitleBean(c.getNameTitle().getNameTitleId(),c.getNameTitle().getName()));
			
			
			
			for(Contract con : c.getContract()) {
				conb = new ContractBean();
				conb.setMotorcycle(new MotorcycleBean(con.getMotorcycle().getRegistrationNumber()+" "+con.getMotorcycle().getRegistrationProvince().getName()));
				conb.setCode(con.getCode());
				conb.setStatus(con.getStatus());
				if(con.getCustomer().getCustomerId() == c.getCustomerId()) {
					conb.setCustomerStatus("Customer");
				}
				else if(con.getGuarantor().getCustomerId() == c.getCustomerId()){
					conb.setCustomerStatus("Guarantor");
				}
				cb.getContract().add(conb);
			}
			
		return cb;
	}
	
	@Transactional
	@Override
	public CustomerBean getCustomerDataByFirebaseId(String fid) {
		String d = null,m = null,y = null;
		Customer c = customerRp.findByFirebaseIdAndIsDeletedFalse(fid);
		CustomerBean cb = null;
		AddressBean ab = null, wb = null;
		ContractBean conb = null;
			if(c.getBirthDay()!=null) {
				d = c.getBirthDay().toString().substring(8, 10);
				m = c.getBirthDay().toString().substring(5, 7);
				y = c.getBirthDay().toString().substring(0, 4);
			}
			cb = new CustomerBean(c.getCustomerId(),c.getCitizenId(),c.getFName(),c.getLName(),c.getTelephone(),d,m,y);
			if(c.getAddress()!=null && c.getAddress().getSubdistrict()!=null) {
				ab = new AddressBean();
				ab.setDetail(c.getAddress().getDetail()+ " " + c.getAddress().getSubdistrict().getName()+" "+c.getAddress().getSubdistrict().getDistrict().getName()+" จังหวัด"+c.getAddress().getSubdistrict().getDistrict().getProvince().getName()+" "+c.getAddress().getSubdistrict().getZipcode());
				cb.setAddress(ab);
			}
			if(c.getWorkplace()!=null && c.getWorkplace().getSubdistrict()!=null) {
				wb = new AddressBean();
				wb.setDetail(c.getWorkplace().getDetail()+ " " + c.getWorkplace().getSubdistrict().getName()+" "+c.getWorkplace().getSubdistrict().getDistrict().getName()+" จังหวัด"+c.getWorkplace().getSubdistrict().getDistrict().getProvince().getName()+" "+c.getWorkplace().getSubdistrict().getZipcode());
				cb.setWorkplace(wb);
			}
			if(c.getNameTitle()!=null)
			cb.setNameTitle(new NameTitleBean(c.getNameTitle().getNameTitleId(),c.getNameTitle().getName()));
			
			
			
			for(Contract con : c.getContract()) {
				conb = new ContractBean();
				conb.setMotorcycle(new MotorcycleBean(con.getMotorcycle().getRegistrationNumber()+" "+con.getMotorcycle().getRegistrationProvince().getName()));
				conb.setCode(con.getCode());
				conb.setStatus(con.getStatus());
				if(con.getCustomer().getCustomerId() == c.getCustomerId()) {
					conb.setCustomerStatus("Customer");
				}
				else if(con.getGuarantor().getCustomerId() == c.getCustomerId()){
					conb.setCustomerStatus("Guarantor");
				}
				cb.getContract().add(conb);
			}
			
		return cb;
	}

	@Transactional
	@Override
	public CustomerBean getCustomerName(String fid) {
		Customer c = customerRp.findByFirebaseIdAndIsDeletedFalse(fid);
		CustomerBean cb = new CustomerBean();
		cb.setFName(c.getFName());
		cb.setLName(c.getLName());
		return cb;
	}

}
