package com.kamonkit.main.service;

import java.util.List;


import com.kamonkit.main.bean.BrandBean;
import com.kamonkit.main.bean.ColorBean;
import com.kamonkit.main.bean.DistrictBean;
import com.kamonkit.main.bean.ModelBean;
import com.kamonkit.main.bean.NameTitleBean;
import com.kamonkit.main.bean.ProvinceBean;
import com.kamonkit.main.bean.SubdistrictBean;
import com.kamonkit.main.entity.Motorcycle.MotorcycleStatus;

public interface ParameterService {
	
	void setAllLocationData();

	List<BrandBean> getAllBrandData();

	List<ProvinceBean> getAllProvinceData();

	List<ColorBean> getAllColorData();

	List<NameTitleBean> getAllNameTitleData();

	List<ProvinceBean> getAllProvince();

	List<MotorcycleStatus> getAllStatusMotorcycleData();

	List<DistrictBean> getAllDistrict(long provinceId);

	List<SubdistrictBean> getAllSubdistrict(long districtId);

	List<BrandBean> getAllBrand();

	List<ModelBean> getAllModelById(long brandId);

	void insertBeand(BrandBean brand, String createdBy);

	void updateBrand(BrandBean brand, String updatedBy);

	void insertModel(ModelBean model, long brandId, String createdBy);

	void updateModel(ModelBean model, String updatedBy);

	void insertColor(ColorBean color, String createdBy);

	void updateColor(ColorBean color, String updatedBy);

	void insertNameTitle(NameTitleBean nameTitle, String createdBy);

	void updateNameTitle(NameTitleBean nameTitle, String updatedBy);
}
