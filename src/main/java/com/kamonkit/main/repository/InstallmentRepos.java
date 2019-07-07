package com.kamonkit.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamonkit.main.entity.Installment;

@Repository
public interface InstallmentRepos extends JpaRepository<Installment,Long>{

	public Installment findByInstallmentIdAndIsDeletedFalse(long id);
	
	List<Installment> findAllByContractOrderByNoAsc(long id);

}
