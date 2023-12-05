package com.api.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.entities.Employe;
import com.api.metier.EmployeImpl;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private EmployeImpl daoEmp;
	
	@GetMapping("/home")
	@ResponseBody
	public String home()
	{
		return "Welcome to REST API ";
	}
	
	@GetMapping(value = "/employe", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public  List<Employe> listEmp()
	{
		List<Employe> list = this.daoEmp.getEmploye();
		return list;
	}
	
	@GetMapping(value = "/employe/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Employe getEmploye(@PathVariable("empId") int idEmpl)
	{
		return this.daoEmp.showEmp(idEmpl);
	}
	
	@PostMapping(value = "/add_employe", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public String addEmploye(@RequestBody Employe emp)
	{
		System.out.println(this.daoEmp.createEmploye(emp));
		
		return this.daoEmp.createEmploye(emp);
	}
	
	@PutMapping(value = "/update_employe", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean updateEmploye(@RequestBody Employe emp)
	{
		return this.daoEmp.updateEmploye(emp.getEmpNo(), emp.getUsername());
	}
	
	@DeleteMapping(value = "/delete_employe", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void deleteEmpl(@RequestBody Employe employe)
	{
		this.daoEmp.deleteEmploye(employe.getEmpNo());
	}
	
	
}
