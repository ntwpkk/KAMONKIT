package com.kamonkit.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamonkit.main.entity.District;

@Repository
public interface DistrictRepos extends JpaRepository<District,Long>{

	public District findByDistrictIdAndIsDeletedFalse(long id);

}
