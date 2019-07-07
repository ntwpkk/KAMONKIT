package com.kamonkit.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamonkit.main.entity.Brand;

@Repository
public interface BrandRepos extends JpaRepository<Brand,Long>{
	
	public Brand findByBrandIdAndIsDeletedFalse(long id);
	
	public List<Brand> findAllByIsDeletedFalseOrderByNameAsc();

}
