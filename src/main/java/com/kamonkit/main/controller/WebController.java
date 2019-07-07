package com.kamonkit.main.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//import com.example.demo.bean.EmployeesBean;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class WebController {

	

	@GetMapping("/")
	public String goLogin() {

//		String uid = "2fR5QH8IkxZVgz2d27ZxPQRcfJA2";
//		UserRecord userRecord = null;
//		try {
//			userRecord = FirebaseAuth.getInstance().getUser(uid);
//		} catch (FirebaseAuthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// See the UserRecord reference doc for the contents of userRecord.
//		System.out.println("Successfully fetched user data: " + userRecord.getUid());
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		String email = "ttt@gmail.com";
//		try {
//			userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
//		} catch (FirebaseAuthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// See the UserRecord reference doc for the contents of userRecord.
//		System.out.println("Successfully fetched user data: " + userRecord.getEmail()+"id : "+userRecord.getUid());
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		// Start listing users from the beginning, 1000 at a time.
//		ListUsersPage page = null;
//		try {
//			page = FirebaseAuth.getInstance().listUsers(null);
//		} catch (FirebaseAuthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		while (page != null) {
//		  for (ExportedUserRecord user : page.getValues()) {
//		    System.out.println("User: " + user.getUid());
//		  }
//		  page = page.getNextPage();
//		}
//
//		// Iterate through all users. This will still retrieve users in batches,
//		// buffering no more than 1000 users in memory at a time.
//		try {
//			page = FirebaseAuth.getInstance().listUsers(null);
//		} catch (FirebaseAuthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for (ExportedUserRecord user : page.iterateAll()) {
//		  System.out.println("User: " + user.getUid()+" 	email : "+user.getEmail());
//		}
		
		
        
		return "index";
	}
	
	@GetMapping("/list")
	public String listUser() {
		ListUsersPage page = null;
		int i=0;
		try {
			page = FirebaseAuth.getInstance().listUsers(null);
		} catch (FirebaseAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ExportedUserRecord user : page.iterateAll()) {
		  System.out.println(++i+" ID: " + user.getUid()+" 	email : "+user.getEmail());
		}
		return "index";
	}
	
	@GetMapping("/report")
    public void reporttt(HttpServletResponse response) throws JRException, IOException, Exception {
    	System.out.println("Step_1");
    	
    	
    	Map<String, Object> params = new HashMap<String, Object>();
        params.put("PARAM_TEST_1", "PARAM___Tae555555555555");
         JasperPrint jasperPrint = null;
         String path="src/main/resources/static/report/contract.jasper",path2="/Users/nattawutpanyakamonkit/JaspersoftWorkspace/MyReports/Blank_A4.jasper";
         
         InputStream jasperStream = new FileInputStream(new File(path));
         
         try {
          
         // jasperStream = this.getClass().getClassLoader().getResourceAsStream(path);
//          File is = null;
         // JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(path2);
          
         // System.out.println("Step_inputStream : "+jasperStream);
          JasperReport report = (JasperReport) JRLoader.loadObject(jasperStream);
//          jasperPrint = JasperFillManager.fillReport(report, params);//.fillReport(jasperReport, params, customRepos.getCounnection());
//          List<MasterLocation> beanList = msRepos.findAll() ;
          
          Collection<Map<String,Object>> beanCollection = new ArrayList<>();
          
//          for (MasterLocation masterLocation : beanList) {
//        	  Map<String,Object> test = new HashMap<>();
//        	  test.put("LATITUDE", masterLocation.getLatitude());
//        	  beanCollection.add(test);
//		}
          
          
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(beanCollection);
		jasperPrint=JasperFillManager.fillReport(report, params,dataSource);
          System.out.println("Step_jr_print");
         } catch (JRException e) {
          throw e;
         } finally {
        	 System.out.println("Step_3");
         // if (jasperStream != null) jasperStream.close();
         }
         
         ServletOutputStream out = response.getOutputStream();
         JasperExportManager.exportReportToPdfStream(jasperPrint, out);
         out.close();
	}
	
	
	@GetMapping("/testssl")
    public String welcome() {
        return "testssl";
    }
	
	
	
}
