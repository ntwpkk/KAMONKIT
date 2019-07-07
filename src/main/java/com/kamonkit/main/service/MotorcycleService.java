package com.kamonkit.main.service;

import java.util.List;

import com.kamonkit.main.bean.MotorcycleBean;
import com.kamonkit.main.entity.Motorcycle.MotorcycleStatus;
import com.kamonkit.main.entity.Motorcycle.MotorcycleType;

public interface MotorcycleService {

	MotorcycleBean getMotorcycleData();
	
	void insertMotorcycle(MotorcycleBean motorBean,String createdBy);

	List<MotorcycleStatus> getAllMotorcycleStatus();

	List<MotorcycleType> getAllMotorcycleType();

	MotorcycleBean getMotorcycleByRegistNumber(String number, long province);

	MotorcycleBean getMotorcycleByEngineNumber(String number);

	MotorcycleBean getMotorcycleByChassisNumber(String number);

	void updateChassis(MotorcycleBean motorBean, String updatedBy);

	void updateEngine(MotorcycleBean motorBean, String updatedBy);

	void updatecolor(MotorcycleBean motorBean, String updatedBy);

	void updateRegistrationNumber(MotorcycleBean motorBean, String updatedBy);
}
