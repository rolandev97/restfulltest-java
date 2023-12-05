package com.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.entities.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Integer>{

	@Modifying
	@Query("UPDATE Employe e SET e.username =:name WHERE e.empNo =:empId")
	public int updateEmp(@Param("empId") int empId,@Param("name") String name);
	
	public Employe findPasswordByUsername(String username);
}
