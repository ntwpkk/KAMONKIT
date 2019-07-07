package com.kamonkit.main.service.implement;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kamonkit.main.bean.BrandBean;
import com.kamonkit.main.bean.ColorBean;
import com.kamonkit.main.bean.ModelBean;
import com.kamonkit.main.bean.MotorcycleBean;
import com.kamonkit.main.bean.ProvinceBean;
import com.kamonkit.main.entity.Color;
import com.kamonkit.main.entity.Employee;
import com.kamonkit.main.entity.Model;
import com.kamonkit.main.entity.Motorcycle;
import com.kamonkit.main.entity.Motorcycle.MotorcycleStatus;
import com.kamonkit.main.entity.Motorcycle.MotorcycleType;
import com.kamonkit.main.entity.Province;
import com.kamonkit.main.repository.ColorRepos;
import com.kamonkit.main.repository.EmployeeRepos;
import com.kamonkit.main.repository.ModelRepos;
import com.kamonkit.main.repository.MotorcycleRepos;
import com.kamonkit.main.repository.ProvinceRepos;
import com.kamonkit.main.service.MotorcycleService;

@Service
public class MotorcycleImplement implements MotorcycleService{

	@Autowired MotorcycleRepos motorRp;
	@Autowired EmployeeRepos empRp;
	@Autowired ColorRepos colorRp;
	@Autowired ModelRepos modelRp;
	@Autowired ProvinceRepos provinceRp;
	
	@Transactional
	@Override
	public MotorcycleBean getMotorcycleData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void insertMotorcycle(MotorcycleBean motorBean, String createdBy) {
		Employee creator = empRp.findByFirebaseIdAndIsDeletedFalse(createdBy);
		Motorcycle motor;
		Model model;
		Color color;
		Province province;
		
		if(creator.getRole().equals(Employee.Role.ADMINISTRATOR) || creator.getRole().equals(Employee.Role.ACCOUNTANT) && !creator.isDeleted()) {
			
			motor = new Motorcycle(motorBean.getRegistrationNumber(),motorBean.getChassisNumber(),motorBean.getEngineNumber());
			model = modelRp.findByModelIdAndIsDeletedFalse(motorBean.getModel().getModelId());
			province = provinceRp.findByProvinceIdAndIsDeletedFalse(motorBean.getRegistrationProvince().getProvinceId());
			color = colorRp.findByColorIdAndIsDeletedFalse(motorBean.getColor().getColorId());
			
			motor.setCreatedBy(createdBy);
			
			motor.setModel(model);
			model.getMotorcycle().add(motor);
			motor.setRegistrationProvince(province);
			province.getMotorcycle().add(motor);
			motor.setColor(color);
			color.getMotorcycle().add(motor);
			motor.setStatus(motorBean.getStatus());
			motor.setType(motorBean.getType());
			motor.setDownPayment(motorBean.getDownPayment());
			motor.setPrice(motorBean.getPrice());
			motorRp.save(motor);
			System.out.println("success Inserted");
		}
		else {
			System.out.println("Fail Inserted");
		}
		
//		for(Motorcycle m : motorRp.findAll()) {
//			System.out.println("id : "+m.getMotorcycleId());
//			System.out.println("chassis : "+m.getChassisNumber());
//			System.out.println("enjine : "+m.getEngineNumber());
//			System.out.println("");
//		}
	}

	@Transactional
	@Override
	public List<MotorcycleStatus> getAllMotorcycleStatus() {
		List<MotorcycleStatus> status = new ArrayList<MotorcycleStatus>();
		status.add(Motorcycle.MotorcycleStatus.IN);
		status.add(Motorcycle.MotorcycleStatus.OUT);
		return status;
	}

	@Transactional
	@Override
	public List<MotorcycleType> getAllMotorcycleType() {
		List<MotorcycleType> status = new ArrayList<MotorcycleType>();
		status.add(Motorcycle.MotorcycleType.NEW);
		status.add(Motorcycle.MotorcycleType.USED);
		return status;
	}

	@Transactional
	@Override
	public MotorcycleBean getMotorcycleByRegistNumber(String number, long province) {
		Motorcycle m = motorRp.findByRegistrationNumberAndRegistrationProvinceAndIsDeletedFalse(number,provinceRp.findByProvinceIdAndIsDeletedFalse(province));
		if(m != null) {
			MotorcycleBean mb = new MotorcycleBean(m.getMotorcycleId(),m.getStatus(),m.getRegistrationNumber(),m.getChassisNumber(),m.getEngineNumber());
			mb.setPrice(m.getPrice());
			mb.setDownPayment(m.getDownPayment());
			mb.setType(m.getType());
			if(m.getModel()!=null) {
				mb.setBrand(new BrandBean(m.getModel().getBrand().getBrandId(),m.getModel().getBrand().getName()));
				mb.setModel(new ModelBean(m.getModel().getModelId(),m.getModel().getName()));
			}
			if(m.getColor()!=null)
				mb.setColor(new ColorBean(m.getColor().getColorId(),m.getColor().getName()));
			mb.setRegistrationProvince(new ProvinceBean(m.getRegistrationProvince().getProvinceId(),m.getRegistrationProvince().getName()));
			return mb;
		}
		else
			return new MotorcycleBean();
		
	}

	@Transactional
	@Override
	public MotorcycleBean getMotorcycleByEngineNumber(String number) {
		Motorcycle m = motorRp.findByEngineNumberAndIsDeletedFalse(number);
		MotorcycleBean mb = new MotorcycleBean(m.getMotorcycleId(),m.getStatus(),m.getRegistrationNumber(),m.getChassisNumber(),m.getEngineNumber()); 
		mb.setPrice(m.getPrice());
		mb.setDownPayment(m.getDownPayment());
		mb.setType(m.getType());
		if(m.getModel()!=null) {
			mb.setBrand(new BrandBean(m.getModel().getBrand().getBrandId(),m.getModel().getBrand().getName()));
			mb.setModel(new ModelBean(m.getModel().getModelId(),m.getModel().getName()));
		}
		if(m.getColor()!=null)
			mb.setColor(new ColorBean(m.getColor().getColorId(),m.getColor().getName()));
		mb.setRegistrationProvince(new ProvinceBean(m.getRegistrationProvince().getProvinceId(),m.getRegistrationProvince().getName()));
		return mb;

	}

	@Transactional
	@Override
	public MotorcycleBean getMotorcycleByChassisNumber(String number) {
		Motorcycle m = motorRp.findByChassisNumberAndIsDeletedFalse(number);
		MotorcycleBean mb = new MotorcycleBean(m.getMotorcycleId(),m.getStatus(),m.getRegistrationNumber(),m.getChassisNumber(),m.getEngineNumber()); 
		mb.setPrice(m.getPrice());
		mb.setDownPayment(m.getDownPayment());
		mb.setType(m.getType());
		if(m.getModel()!=null) {
			mb.setBrand(new BrandBean(m.getModel().getBrand().getBrandId(),m.getModel().getBrand().getName()));
			mb.setModel(new ModelBean(m.getModel().getModelId(),m.getModel().getName()));
		}
		if(m.getColor()!=null)
			mb.setColor(new ColorBean(m.getColor().getColorId(),m.getColor().getName()));
		mb.setRegistrationProvince(new ProvinceBean(m.getRegistrationProvince().getProvinceId(),m.getRegistrationProvince().getName()));
		return mb;
	}

	@Transactional
	@Override
	public void updateChassis(MotorcycleBean motorBean, String updatedBy) {
		Motorcycle m = motorRp.findByMotorcycleIdAndIsDeletedFalse(motorBean.getMotorcycleId());
		if(m!=null) {
			m.setMotorcycleId(m.getMotorcycleId());
			m.setUpdatedBy(updatedBy);
			
			m.setChassisNumber(motorBean.getChassisNumber());
			motorRp.save(m);
		}
		
	}

	@Transactional
	@Override
	public void updateEngine(MotorcycleBean motorBean, String updatedBy) {
		Motorcycle m = motorRp.findByMotorcycleIdAndIsDeletedFalse(motorBean.getMotorcycleId());
		if(m!=null) {
			m.setMotorcycleId(m.getMotorcycleId());
			m.setUpdatedBy(updatedBy);
			
			m.setEngineNumber(motorBean.getEngineNumber());
			motorRp.save(m);
		}
		
	}

	@Transactional
	@Override
	public void updatecolor(MotorcycleBean motorBean, String updatedBy) {
		Motorcycle m = motorRp.findByMotorcycleIdAndIsDeletedFalse(motorBean.getMotorcycleId());
		Color c = null;
		if(m!=null) {
			m.setMotorcycleId(m.getMotorcycleId());
			m.setUpdatedBy(updatedBy);
			c = colorRp.findByColorIdAndIsDeletedFalse(motorBean.getColor().getColorId());
			c.getMotorcycle().add(m);
			m.setColor(c);
			motorRp.save(m);
		}
		
	}

	@Transactional
	@Override
	public void updateRegistrationNumber(MotorcycleBean motorBean, String updatedBy) {
		Motorcycle m = motorRp.findByMotorcycleIdAndIsDeletedFalse(motorBean.getMotorcycleId());
		Province p = null;
		if(m!=null) {
			m.setMotorcycleId(m.getMotorcycleId());
			m.setUpdatedBy(updatedBy);
			p = provinceRp.findByProvinceIdAndIsDeletedFalse(motorBean.getRegistrationProvince().getProvinceId());
			p.getMotorcycle().add(m);
			m.setRegistrationProvince(p);
			m.setRegistrationNumber(motorBean.getRegistrationNumber());
			motorRp.save(m);
		}
		
	}
	
	
	
	

}
