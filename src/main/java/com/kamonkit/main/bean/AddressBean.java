package com.kamonkit.main.bean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class AddressBean {

	private long addressId;
	private String detail;
	private ProvinceBean province;
	private DistrictBean district;
	private SubdistrictBean subdistrict;
	private long zipcode;
	
	public AddressBean() {}

	public AddressBean(long addressId, String detail, ProvinceBean province, DistrictBean district,
			SubdistrictBean subdistrict, long zipcode) {
		super();
		this.addressId = addressId;
		this.detail = detail;
		this.province = province;
		this.district = district;
		this.subdistrict = subdistrict;
		this.zipcode = zipcode;
	}

	
	
}
