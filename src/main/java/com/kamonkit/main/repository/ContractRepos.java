package com.kamonkit.main.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamonkit.main.entity.Contract;

@Repository
public interface ContractRepos extends JpaRepository<Contract,Long>{

	Contract findByContractIdAndIsDeletedFalse(long id);
	
	Contract findByCreatedDate(Date date);

	List<Contract> findAllByCreatedDate(Date date);
	
	List<Contract> findAllByCreatedDateBetween(Date start, Date end);
	
	Contract findByCodeAndIsDeletedFalse(String code);
}
