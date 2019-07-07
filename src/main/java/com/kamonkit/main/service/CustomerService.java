package com.kamonkit.main.service;

import com.kamonkit.main.bean.CustomerBean;

public interface CustomerService {

	CustomerBean getCustomerByCitizenId(String citizenId);

	void insertCustomer(String createdBy, CustomerBean cb);
	
	CustomerBean getCustomerByName(String fname, String lname);

	void updateAddress(String updatedBy, CustomerBean cb);

	void updateName(String updatedBy, CustomerBean cb);

	void updateTelephone(String updatedBy, CustomerBean cb);

	CustomerBean getCustomerDataByCitizenId(String id);

	CustomerBean getCustomerDataByName(String fname, String lname);

	CustomerBean getCustomerDataByFirebaseId(String fid);

	CustomerBean getCustomerName(String fid);

}
