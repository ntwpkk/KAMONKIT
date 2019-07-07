package com.kamonkit.main.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.kamonkit.main.entity.Address;
import com.kamonkit.main.entity.Brand;
import com.kamonkit.main.entity.Color;
import com.kamonkit.main.entity.Customer;
import com.kamonkit.main.entity.District;
import com.kamonkit.main.entity.Employee;
import com.kamonkit.main.entity.Model;
import com.kamonkit.main.entity.Motorcycle;
import com.kamonkit.main.entity.NameTitle;
import com.kamonkit.main.entity.Province;
import com.kamonkit.main.entity.Subdistrict;
import com.kamonkit.main.repository.BrandRepos;
import com.kamonkit.main.repository.ColorRepos;
import com.kamonkit.main.repository.CustomerRepos;
import com.kamonkit.main.repository.DistrictRepos;
import com.kamonkit.main.repository.EmployeeRepos;
import com.kamonkit.main.repository.InstallmentRepos;
import com.kamonkit.main.repository.ModelRepos;
import com.kamonkit.main.repository.MotorcycleRepos;
import com.kamonkit.main.repository.NameTitleRepos;
import com.kamonkit.main.repository.ProvinceRepos;
import com.kamonkit.main.repository.SubdistrictRepos;

@Component
public class StartUp {

	@Autowired CustomerRepos custRp;
	@Autowired EmployeeRepos empRp;
	@Autowired MotorcycleRepos motorRp;
	@Autowired ColorRepos colorRp;
	@Autowired BrandRepos brandRp;
	@Autowired ModelRepos modelRp;
	@Autowired ProvinceRepos provRp;
	@Autowired NameTitleRepos nameTitleRp;
	@Autowired DistrictRepos distRp;
	@Autowired SubdistrictRepos subDistRp;
	@Autowired InstallmentRepos instRp;
	
	@PostConstruct
	public void startup() {
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream("src/main/resources/kamonkit-motorcycle-firebase-adminsdk-4ic3v-54a2f26710.json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FirebaseOptions options = null;
		try {
			options = new FirebaseOptions.Builder()
			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			  .setDatabaseUrl("https://kamonkit-motorcycle.firebaseio.com")
			  .build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FirebaseApp.initializeApp(options);
		
		
		if(nameTitleRp.findAll().size()==0) {
			NameTitle nt1 = new NameTitle("นาย"),nt2 = new NameTitle("นาง"),nt3 = new NameTitle("นางสาว");
			nameTitleRp.save(nt1);
			nameTitleRp.save(nt2);
			nameTitleRp.save(nt3);
		}
		
		
		
		if(custRp.findAll().size()==0) {
			Customer cust1 = new Customer();
			cust1.setCitizenId("1100702444364");
			cust1.setFName("Ransiwoot");
			cust1.setLName("Boem");
			cust1.setTelephone("0899380454");
			Address add1 = new Address(),add2 = new Address();
			add1.setDetail("custaddressนะ");
			add2.setDetail("custworkplaceจ๊ะ");
			cust1.setAddress(add1);
			cust1.setWorkplace(add2);
			custRp.save(cust1);
		}
		
		if(empRp.findAll().size()==0) {
			Employee emp1 = new Employee();
			emp1.setFirebaseId("eRMGnywJMXWpzo2FQrDL4C6m2rM2");
			emp1.setFName("Nattawut");
			emp1.setLName("Panyakamonkit");
			emp1.setRole(Employee.Role.ADMINISTRATOR);
			empRp.save(emp1);
			Customer c = new Customer();
			
		}
		
		if(brandRp.findAll().size()==0) {
			
			
			Brand b1 = new Brand("Yamaha"),b2 = new Brand("Honda"),b3 = new Brand("Kawasaki");
			Model m1 = new Model("Filano"),m2 = new Model("Spark"),m3 = new Model("Wave"),m4 = new Model("MSX"),m5 = new Model("KSR"),m6 = new Model("NSX");
			
			
			Color c1 = new Color("ดำ-แดง"),c2 = new Color("ขาว-ดำ"),c3 = new Color("น้ำเงิน-ดำ"),c4 = new Color("แดง-ขาว");
			
			Motorcycle motor = new Motorcycle();
			motor.setChassisNumber("tae");
			motor.setEngineNumber("taee");
			motor.setRegistrationNumber("taeee");
			motor.setColor(c1);
			c1.getMotorcycle().add(motor);

			
			colorRp.save(c1);
			colorRp.save(c2);
			colorRp.save(c3);
			colorRp.save(c4);
			
			b1.getModel().add(m1);
			b1.getModel().add(m2);
			b2.getModel().add(m3);
			b2.getModel().add(m4);
			b3.getModel().add(m5);
			b3.getModel().add(m6);
			m1.setBrand(b1);
			m2.setBrand(b1);
			m3.setBrand(b2);
			m4.setBrand(b2);
			m5.setBrand(b3);
			m6.setBrand(b3);
			
			brandRp.save(b1);
			brandRp.save(b2);
			brandRp.save(b3);
			motorRp.save(motor);
		}
		
		if(provRp.findAllByIsDeletedFalseOrderByProvinceIdAsc().size()==0) {
			Province prov = null;
			District dist = null;
			Subdistrict subdist;
			XSSFRow row;
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(new File("src/main/resources/static/datasource/location.xlsx"));
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			XSSFSheet worksheet = workbook.getSheetAt(0);
			for(int i=2;i<7429/*worksheet.getPhysicalNumberOfRows()*/;i++) {
				row = worksheet.getRow(i);
				if(i==2) {
					
					prov = new Province();
					prov.setName(row.getCell(1).getStringCellValue().trim());
					
					dist = new District();
					dist.setName(row.getCell(2).getStringCellValue().trim());
					dist.setProvince(prov);
					prov.getDistrict().add(dist);
					
					subdist = new Subdistrict();
					subdist.setName(row.getCell(3).getStringCellValue().trim());
					subdist.setZipcode((long)row.getCell(4).getNumericCellValue());
					subdist.setDistrict(dist);
					dist.getSubdistrict().add(subdist);
				}
				else {
					if(i==7428) {
						provRp.save(prov);
					}
					else if(!row.getCell(1).getStringCellValue().equals(worksheet.getRow(i-1).getCell(1).getStringCellValue())) {
						System.out.println("						Change Province");
						provRp.save(prov);
						prov = null;
						prov = new Province();
						prov.setName(row.getCell(1).getStringCellValue().trim());
					}
					
					
					if( (i!=7428) && !row.getCell(2).getStringCellValue().equals(worksheet.getRow(i-1).getCell(2).getStringCellValue())) {
						System.out.println("						Change District");
						dist = new District();
						dist.setName(row.getCell(2).getStringCellValue().trim());
						dist.setProvince(prov);
						prov.getDistrict().add(dist);
					}
					if(i!=7428) {
						subdist = new Subdistrict();
						subdist.setName(row.getCell(3).getStringCellValue().trim());
						subdist.setZipcode((long)row.getCell(4).getNumericCellValue());
						subdist.setDistrict(dist);
						dist.getSubdistrict().add(subdist);
						System.out.println("pro "+prov.getName()+" dist "+dist.getName()+" subdist "+subdist.getName()+"        "+i);
					}
				}
			}
		}
		
	}
	
	
	
}
