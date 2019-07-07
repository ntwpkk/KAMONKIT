package com.kamonkit.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamonkit.main.bean.MotorcycleBean;
import com.kamonkit.main.entity.Motorcycle;
import com.kamonkit.main.entity.Province;

@Repository
public interface MotorcycleRepos extends JpaRepository<Motorcycle,Long>{

	Motorcycle findByMotorcycleIdAndIsDeletedFalse(long id);
	
	Motorcycle findByChassisNumberAndIsDeletedFalse(String number);
	
	Motorcycle findByEngineNumberAndIsDeletedFalse(String number);
	
	Motorcycle findByRegistrationNumberAndIsDeletedFalse(String number);
	
	Motorcycle findByRegistrationNumberAndRegistrationProvinceAndIsDeletedFalse(String number, Province province);
	
	
}
