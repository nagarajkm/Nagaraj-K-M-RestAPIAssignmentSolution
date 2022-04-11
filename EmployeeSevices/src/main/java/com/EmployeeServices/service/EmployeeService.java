package com.EmployeeServices.service;

import java.util.List;


import com.EmployeeServices.entity.Employee;
import com.EmployeeServices.entity.User;
import com.EmployeeServices.entity.Role;

public interface EmployeeService {

	List<Employee> findAll();

	Employee findById(int theId);

	void save(Employee theEmployee);

	void deleteById(int theId);

	List<Employee> searchByFirstName(String firstName);

	List<Employee> sortByFirstName(String order);

	User saveUser(User user);

	Role saveRole(Role role);

	

	

}
