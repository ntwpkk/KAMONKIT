package com.kamonkit.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamonkit.main.entity.Model;

@Repository
public interface ModelRepos extends JpaRepository<Model,Long>{

	Model findByModelIdAndIsDeletedFalse(long id);
	Model findByNameAndIsDeletedFalse(String name);
}
