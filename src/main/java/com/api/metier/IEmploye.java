package com.api.metier;

import java.util.List;
import java.util.Optional;

import com.api.entities.Employe;

public interface IEmploye {

	public String createEmploye(Employe employe);
	public List<Employe> getEmploye();
	public boolean updateEmploye(int empNo, String name);
	public void deleteEmploye(int empNo);
	public Employe showEmp(int employeId);
	public boolean verifPass(Employe empl);
	public Employe getPass(Employe empl);
}
