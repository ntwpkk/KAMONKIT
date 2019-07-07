package com.kamonkit.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamonkit.main.entity.Province;

@Repository
public interface ProvinceRepos extends JpaRepository<Province,Long>{
	
	public Province findByProvinceIdAndIsDeletedFalse(long id);
	
	public List<Province> findAllByIsDeletedFalseOrderByProvinceIdAsc();

}
