package com.api.metier;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dao.EmployeRepository;
import com.api.entities.Employe;

@Service
@Transactional
public class EmployeImpl implements IEmploye{

	@Autowired
	private EmployeRepository repoEmp;
	
	@Override
	public String createEmploye(Employe employe) {
		
		if(this.repoEmp.save(employe) != null)
		{
			return "{statut: 'Registration Success'}";
		}
		
		return "{'statut','Registration failled'}";
	}

	@Override
	public List<Employe> getEmploye() {
		// TODO Auto-generated method stub
		return this.repoEmp.findAll();
	}

	@Override
	public void deleteEmploye(int empNo) {
		// TODO Auto-generated method stub
		this.repoEmp.deleteById(empNo);
	}

	@Override
	public boolean updateEmploye(int empNo, String name) {
		if(this.repoEmp.updateEmp(empNo, name) == 1)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public Employe showEmp(int employeId) {
		// TODO Auto-generated method stub
		return this.repoEmp.findById(employeId).get();
	}

	@Override
	public boolean verifPass(Employe empl) {
		if(empl.getPassword().equals(getPass(empl).getPassword()))
		{
			return true;
		}
		return false;
	}

	@Override
	public Employe getPass(Employe empl) {
		// TODO Auto-generated method stub
		return this.repoEmp.findPasswordByUsername(empl.getUsername());
	}

}
