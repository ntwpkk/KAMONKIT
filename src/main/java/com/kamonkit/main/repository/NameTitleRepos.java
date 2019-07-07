package com.kamonkit.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamonkit.main.entity.NameTitle;

@Repository
public interface NameTitleRepos extends JpaRepository<NameTitle,Long>{
	
	NameTitle findByNameTitleIdAndIsDeletedFalse(long id);
	List<NameTitle> findAllByIsDeletedFalseOrderByNameTitleIdAsc();
	

}
