package com.kamonkit.main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kamonkit.main.bean.BrandBean;
import com.kamonkit.main.bean.ColorBean;
import com.kamonkit.main.bean.DistrictBean;
import com.kamonkit.main.bean.ModelBean;
import com.kamonkit.main.bean.NameTitleBean;
import com.kamonkit.main.bean.ProvinceBean;
import com.kamonkit.main.bean.SubdistrictBean;
import com.kamonkit.main.entity.Motorcycle.MotorcycleStatus;
import com.kamonkit.main.entity.Province;
import com.kamonkit.main.entity.Subdistrict;
import com.kamonkit.main.service.MotorcycleService;
import com.kamonkit.main.service.ParameterService;

@RestController
@RequestMapping("/parameter")
public class ParameterRestController {
	
	@Autowired ParameterService pmService;
	
	@PostMapping("/getAllBrandData")
	public List<BrandBean> getAllBrandData(){
		
		return pmService.getAllBrandData();
	}
	
	@PostMapping("/getAllProvinceData")
	public List<ProvinceBean> getAllProvinceData(){
		return pmService.getAllProvinceData();
	}
	
	@PostMapping("/getAllColorData")
	public List<ColorBean> getAllColorData(){
		return pmService.getAllColorData();
	}
	
	@PostMapping("/setAllLocationData")
	public String setAllLocationData() {
		pmService.setAllLocationData();
		return null;
	}
	
	@PostMapping("/getAllNameTitleData")
	public List<NameTitleBean> getAllNameTitleData(){
		return pmService.getAllNameTitleData();
	}
	
	@PostMapping("/getAllProvince")
	public List<ProvinceBean> getAllProvince(){
		return pmService.getAllProvince();
	}
	
	@PostMapping("/getAllDistrict")
	public List<DistrictBean> getAllDistrict(@RequestParam(value="provinceId")long provinceId){
		return pmService.getAllDistrict(provinceId);
	}
	
	@PostMapping("/getAllSubdistrict")
	public List<SubdistrictBean> getAllSubdistrict(@RequestParam(value="districtId")long districtId){
		return pmService.getAllSubdistrict(districtId);
	}
	
	@PostMapping("/getAllBrand")
	public List<BrandBean> getAllBrand(){
		
		return pmService.getAllBrand();
	}
	
	@PostMapping("/getAllModelById")
	public List<ModelBean> getAllModelById(@RequestParam(value="brandId")long brandId){
		
		return pmService.getAllModelById(brandId);
	}
	
	@PostMapping("/insertBrand")
	public void insertBrand(@RequestParam(value="createdBy")String createdBy, @RequestBody BrandBean brand){
		
	pmService.insertBeand(brand, createdBy);
	}
	
	@PostMapping("/updateBrand")
	public void updateBrand(@RequestParam(value="updatedBy")String updatedBy, @RequestBody BrandBean brand){
		
	pmService.updateBrand(brand, updatedBy);
	}
	
	@PostMapping("/insertModel")
	public void insertModel(@RequestParam(value="createdBy")String createdBy, @RequestParam(value="brandId")long brandId, @RequestBody ModelBean model){
		
	pmService.insertModel(model,brandId, createdBy);
	}
	
	@PostMapping("/updateModel")
	public void updateModel(@RequestParam(value="updatedBy")String updatedBy, @RequestBody ModelBean model){
		
	pmService.updateModel(model, updatedBy);
	}
	
	@PostMapping("/insertColor")
	public void insertColor(@RequestParam(value="createdBy")String createdBy, @RequestBody ColorBean color){
		
	pmService.insertColor(color, createdBy);
	}
	
	@PostMapping("/updateColor")
	public void updateColor(@RequestParam(value="updatedBy")String updatedBy, @RequestBody ColorBean color){
		
	pmService.updateColor(color, updatedBy);
	}
	
	@PostMapping("/insertNameTitle")
	public void insertnameTitle(@RequestParam(value="createdBy")String createdBy, @RequestBody NameTitleBean nameTitle){
		
	pmService.insertNameTitle(nameTitle, createdBy);
	}
	
	@PostMapping("/updateNameTitle")
	public void updatenameTitle(@RequestParam(value="updatedBy")String updatedBy, @RequestBody NameTitleBean nameTitle){
		
	pmService.updateNameTitle(nameTitle, updatedBy);
	}

}
