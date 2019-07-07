package com.kamonkit.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class KamonkitApplication {

	public static void main(String[] args) {
//		SpringApplication.run(KamonkitApplication.class, args);

        SpringApplication application = new SpringApplication(KamonkitApplication.class);
		application.setAdditionalProfiles("ssl");
	    application.run(args);
	   
	}
	

}

