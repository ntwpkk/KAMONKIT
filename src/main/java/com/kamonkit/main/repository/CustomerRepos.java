package com.kamonkit.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamonkit.main.entity.Customer;

@Repository
public interface CustomerRepos extends JpaRepository<Customer,Long>{

	Customer findByFirebaseIdAndIsDeletedFalse(String id);
	Customer findByCustomerIdAndIsDeletedFalse(long id);
	Customer findByCitizenIdAndIsDeletedFalse(String citizenId);
	Customer findByFNameAndLNameAndIsDeletedFalse(String fName, String lName);
}
