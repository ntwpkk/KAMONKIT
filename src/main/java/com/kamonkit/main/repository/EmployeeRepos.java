package com.kamonkit.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamonkit.main.entity.Employee;

@Repository
public interface EmployeeRepos  extends JpaRepository<Employee,Long> {

	Employee findByFirebaseIdAndIsDeletedFalse(String id);
	
	List<Employee> findAllByIsDeletedFalse();
	
}
