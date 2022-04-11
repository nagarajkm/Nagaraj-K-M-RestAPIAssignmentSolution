package com.EmployeeServices.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EmployeeServices.entity.Employee;

public interface EmployeeRespository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByFirstNameContainsAllIgnoreCase(String firstName);
	
	List<Employee> findAllByOrderByFirstNameAsc();
	
	List<Employee> findAllByOrderByFirstNameDesc();
	
	
	

}
 