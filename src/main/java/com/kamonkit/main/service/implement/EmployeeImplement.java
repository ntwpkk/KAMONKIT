package com.kamonkit.main.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kamonkit.main.bean.EmployeeBean;
import com.kamonkit.main.entity.Employee;
import com.kamonkit.main.entity.Employee.Role;
import com.kamonkit.main.repository.EmployeeRepos;
import com.kamonkit.main.service.EmployeeService;

@Service
public class EmployeeImplement implements EmployeeService{

	@Autowired EmployeeRepos empRp;
	@Autowired UserImplements userService;
	
	@Transactional
	@Override
	public EmployeeBean getEmployeeData(String fid) {
		Employee emp = empRp.findByFirebaseIdAndIsDeletedFalse(fid);
		EmployeeBean empBean = new EmployeeBean(emp.getFName(),emp.getLName());
		return empBean;
	}

	@Transactional
	@Override
	public Role checkRoleUser(String fid) {
		Employee emp = new Employee();
		emp = empRp.findByFirebaseIdAndIsDeletedFalse(fid);
		if(emp!=null && (emp.getRole()==Employee.Role.ADMINISTRATOR || emp.getRole()==Employee.Role.ACCOUNTANT || emp.getRole()==Employee.Role.INVESTIGATOR)) {
			return emp.getRole();
		}
		else {
			return null;
		}
	}

	@Transactional
	@Override
	public void insertEmployee(String createdBy, EmployeeBean eb) {
		Employee emp = empRp.findByFirebaseIdAndIsDeletedFalse(createdBy);
		if(emp!=null && emp.getRole().equals(Employee.Role.ADMINISTRATOR)) {
			Employee e = new Employee(eb.getFName(), eb.getLName(), eb.getTelephone());
			e.setRole(eb.getRole());
			e.setCitizenId(eb.getCitizenId());
			e.setFirebaseId(userService.createUser(eb.getCitizenId()+"@kamonkit.com"));
			empRp.save(e);
		}
	}
	
	@Transactional
	@Override
	public List<EmployeeBean> getAllEmployee() {
		List<Employee> el = empRp.findAllByIsDeletedFalse();
		List<EmployeeBean> ebl = new ArrayList<EmployeeBean>();
		for(Employee e : el) {
			if(e.getRole().equals(Employee.Role.ADMINISTRATOR)) continue;
			
			ebl.add(new EmployeeBean(e.getEmployeeId(), e.getCitizenId(), e.getFName(), e.getLName(), e.getTelephone(), e.getRole()) );
		}
		
		return ebl;
	}

}
