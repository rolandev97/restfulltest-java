package com.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.entities.Employe;
import com.api.metier.EmployeImpl;

@SpringBootApplication
public class RestfulltestApplication implements CommandLineRunner{

	@Autowired
	private EmployeImpl daoEmp;
	
	public static void main(String[] args) {
		SpringApplication.run(RestfulltestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		
	}

}
