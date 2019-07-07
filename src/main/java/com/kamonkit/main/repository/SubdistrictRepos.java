package com.kamonkit.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamonkit.main.entity.Subdistrict;

@Repository
public interface SubdistrictRepos extends JpaRepository<Subdistrict,Long>{

	public Subdistrict findBySubdistrictIdAndIsDeletedFalse(long id);

}
