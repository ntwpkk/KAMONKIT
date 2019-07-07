package com.kamonkit.main.service.implement;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kamonkit.main.bean.BrandBean;
import com.kamonkit.main.bean.ColorBean;
import com.kamonkit.main.bean.DistrictBean;
import com.kamonkit.main.bean.ModelBean;
import com.kamonkit.main.bean.NameTitleBean;
import com.kamonkit.main.bean.ProvinceBean;
import com.kamonkit.main.bean.SubdistrictBean;
import com.kamonkit.main.entity.Brand;
import com.kamonkit.main.entity.Color;
import com.kamonkit.main.entity.District;
import com.kamonkit.main.entity.Model;
import com.kamonkit.main.entity.Motorcycle;
import com.kamonkit.main.entity.Motorcycle.MotorcycleStatus;
import com.kamonkit.main.entity.NameTitle;
import com.kamonkit.main.entity.Province;
import com.kamonkit.main.entity.Subdistrict;
import com.kamonkit.main.repository.BrandRepos;
import com.kamonkit.main.repository.ColorRepos;
import com.kamonkit.main.repository.DistrictRepos;
import com.kamonkit.main.repository.ModelRepos;
import com.kamonkit.main.repository.NameTitleRepos;
import com.kamonkit.main.repository.ProvinceRepos;
import com.kamonkit.main.repository.SubdistrictRepos;
import com.kamonkit.main.service.ParameterService;

@Service
public class ParameterImplement implements ParameterService{
	
	@Autowired BrandRepos brandRp;
	@Autowired ColorRepos colorRp;
	@Autowired ModelRepos modelRp;
	@Autowired ProvinceRepos provRp;
	@Autowired DistrictRepos distRp;
	@Autowired NameTitleRepos nameTitleRp;
	@Autowired SubdistrictRepos subdistRp;
	
	@Transactional
	@Override
	public List<BrandBean> getAllBrandData() {
		List<BrandBean> brandBeans = new ArrayList<BrandBean>();
		List<Brand> brands = new ArrayList<Brand>();
		List<ModelBean> modelBeans = new ArrayList<ModelBean>();
		BrandBean brandBean;
		ModelBean modelBean;
		brands = brandRp.findAllByIsDeletedFalseOrderByNameAsc();
		for(Brand b1 : brands) {
//			System.out.println(b1.getBrandId()+"  "+b1.getName());
			brandBean = new BrandBean(b1.getBrandId(),b1.getName());
			for(Model m1 : b1.getModel()) {
				if(m1!=null && !m1.isDeleted()) {
//					System.out.println(m1.getModelId()+"                     "+m1.getName());
					modelBean = new ModelBean(m1.getModelId(),m1.getName());
					modelBeans.add(modelBean);
				}
			}
			brandBean.setModel(modelBeans);
			brandBeans.add(brandBean);
			brandBean = null;
			modelBeans = null;
			modelBeans = new ArrayList<ModelBean>();
		}
		return brandBeans;
	}

	@Transactional
	@Override
	public List<ProvinceBean> getAllProvinceData() {
		List<Province> provinces = new ArrayList<Province>();
		List<ProvinceBean> provinceBeans = new ArrayList<ProvinceBean>();
		List<DistrictBean> districtBeans = new ArrayList<DistrictBean>();
		List<SubdistrictBean> subdistrictBeans = new ArrayList<SubdistrictBean>();
		ProvinceBean provinceBean;
		DistrictBean districtBean;
		provinces = provRp.findAllByIsDeletedFalseOrderByProvinceIdAsc();
		
		for(Province p1 : provinces) {
			provinceBean = new ProvinceBean(p1.getProvinceId(),p1.getName());
			for(District d1: p1.getDistrict()) {
				districtBean = new DistrictBean(d1.getDistrictId(),d1.getName());
				for(Subdistrict s1 : d1.getSubdistrict()) {
					subdistrictBeans.add(new SubdistrictBean(s1.getSubdistrictId(),s1.getName(),s1.getZipcode()));
				}
				districtBean.setSubdistrict(subdistrictBeans);
				districtBeans.add(districtBean);
				districtBean = null;
				subdistrictBeans = null;
				subdistrictBeans = new ArrayList<SubdistrictBean>();
			}
			provinceBean.setDistrict(districtBeans);
			provinceBeans.add(provinceBean);
			provinceBean = null;
			districtBeans = null;
			districtBeans = new ArrayList<DistrictBean>();
		}
		return provinceBeans;
	}

	@Transactional
	@Override
	public List<ColorBean> getAllColorData() {
		List<Color> colors = new ArrayList<Color>();
		List<ColorBean> colorBeans = new ArrayList<ColorBean>();
		colors = colorRp.findAllByIsDeletedFalseOrderByNameAsc();
		for(Color c1 : colors) {
			colorBeans.add(new ColorBean(c1.getColorId(),c1.getName()));
		}
		return colorBeans;
	}

	

	@Transactional
	@Override
	public List<NameTitleBean> getAllNameTitleData() {
		List<NameTitleBean> nameTitleBeans = new ArrayList<NameTitleBean>();
		List<NameTitle> nameTitles = new ArrayList<NameTitle>();
		nameTitles = nameTitleRp.findAllByIsDeletedFalseOrderByNameTitleIdAsc();
		for(NameTitle nt1 : nameTitles) {
			nameTitleBeans.add(new NameTitleBean(nt1.getNameTitleId(),nt1.getName()));
		}
		return nameTitleBeans;
	}

	@Transactional
	@Override
	public List<ProvinceBean> getAllProvince() {
		List<Province> provinces = new ArrayList<Province>();
		List<ProvinceBean> provinceBeans = new ArrayList<ProvinceBean>();
		ProvinceBean provinceBean;
		provinces = provRp.findAllByIsDeletedFalseOrderByProvinceIdAsc();
		for(Province p1 : provinces) {
			provinceBean = new ProvinceBean(p1.getProvinceId(),p1.getName());
			provinceBeans.add(provinceBean);
			provinceBean = null;
		}
		return provinceBeans;
	}

	@Transactional
	@Override
	public List<MotorcycleStatus> getAllStatusMotorcycleData() {
		List<MotorcycleStatus> status = new ArrayList<MotorcycleStatus>();
		status.add(Motorcycle.MotorcycleStatus.IN);
		status.add(Motorcycle.MotorcycleStatus.OUT);
		return status;
	}

	@Override
	public void setAllLocationData() {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public List<DistrictBean> getAllDistrict(long provinceId) {
		List<DistrictBean> districtBean = new ArrayList<DistrictBean>();
		for(District d : provRp.findByProvinceIdAndIsDeletedFalse(provinceId).getDistrict()) {
			System.out.println(d.getName());
			districtBean.add(new DistrictBean(d.getDistrictId(),d.getName()));
		}
		return districtBean;
	}

	@Transactional
	@Override
	public List<SubdistrictBean> getAllSubdistrict(long districtId) {
		List<SubdistrictBean> subdistrictBean = new ArrayList<SubdistrictBean>();
		for(Subdistrict d : distRp.findByDistrictIdAndIsDeletedFalse(districtId).getSubdistrict()) {
			subdistrictBean.add(new SubdistrictBean(d.getSubdistrictId(),d.getName(),d.getZipcode()));
		}
		return subdistrictBean;
	}

	@Transactional
	@Override
	public List<BrandBean> getAllBrand() {
		List<BrandBean> brandBeans = new ArrayList<BrandBean>();
		for(Brand b : brandRp.findAllByIsDeletedFalseOrderByNameAsc()) {
			brandBeans.add(new BrandBean(b.getBrandId(),b.getName()));
		}
		return brandBeans;
	}

	@Transactional
	@Override
	public List<ModelBean> getAllModelById(long brandId) {
		List<ModelBean> modelBeans = new ArrayList<ModelBean>();
		for(Model m : brandRp.findByBrandIdAndIsDeletedFalse(brandId).getModel()) {
			modelBeans.add(new ModelBean(m.getModelId(),m.getName()));
		}
		return modelBeans;
	}

	@Transactional
	@Override
	public void insertBeand(BrandBean brand, String createdBy) {
		Brand b = new Brand();
		b.setName(brand.getName());
		b.setCreatedBy(createdBy);
		brandRp.save(b);
	}

	@Transactional
	@Override
	public void updateBrand(BrandBean brand, String updatedBy) {
		Brand b = brandRp.findByBrandIdAndIsDeletedFalse(brand.getBrandId());
		b.setName(brand.getName());
		b.setUpdatedBy(updatedBy);
		b.setBrandId(brand.getBrandId());
		brandRp.save(b);
	}

	@Transactional
	@Override
	public void insertModel(ModelBean model, long brandId, String createdBy) {
		Brand b = brandRp.findByBrandIdAndIsDeletedFalse(brandId);
		Model m = new Model(model.getName());
		m.setCreatedBy(createdBy);
		m.setBrand(b);
		b.getModel().add(m);
		modelRp.save(m);
	}

	@Transactional
	@Override
	public void updateModel(ModelBean model, String updatedBy) {
		Model m = modelRp.findByModelIdAndIsDeletedFalse(model.getModelId());
		m.setModelId(model.getModelId());
		m.setName(model.getName());
		m.setUpdatedBy(updatedBy);
		modelRp.save(m);
		
	}

	@Transactional
	@Override
	public void insertColor(ColorBean color, String createdBy) {
		Color c = new Color(color.getName());
		c.setCreatedBy(createdBy);
		colorRp.save(c);
	}

	@Transactional
	@Override
	public void updateColor(ColorBean color, String updatedBy) {
		Color c = colorRp.findByColorIdAndIsDeletedFalse(color.getColorId());
		c.setColorId(color.getColorId());
		c.setName(color.getName());
		c.setUpdatedBy(updatedBy);
		colorRp.save(c);
	}

	@Transactional
	@Override
	public void insertNameTitle(NameTitleBean nameTitle, String createdBy) {
		NameTitle n = new NameTitle(nameTitle.getName());
		n.setCreatedBy(createdBy);
		nameTitleRp.save(n);
	}

	@Transactional
	@Override
	public void updateNameTitle(NameTitleBean nameTitle, String updatedBy) {
		NameTitle n = nameTitleRp.findByNameTitleIdAndIsDeletedFalse(nameTitle.getNameTitleId());
		n.setNameTitleId(nameTitle.getNameTitleId());
		n.setName(nameTitle.getName());
		n.setUpdatedBy(updatedBy);
		nameTitleRp.save(n);
	}

}
