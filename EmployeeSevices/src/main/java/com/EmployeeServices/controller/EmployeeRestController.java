package com.EmployeeServices.controller;

import java.util.Collection;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeServices.entity.Employee;
import com.EmployeeServices.entity.Role;
import com.EmployeeServices.entity.User;
import com.EmployeeServices.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
	
		employeeService = theEmployeeService;
	}
	
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return employeeService.saveUser(user);
		
	}
	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return employeeService.saveRole(role);
		
	}
	
	@GetMapping("/employeeS")
	public List<Employee> findAll(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName=authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return employeeService.findAll();
		
		
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId ) {
		Employee theEmployee=employeeService.findById(employeeId);
		if(theEmployee==null) {
			throw new RuntimeException("Employee id not found "+employeeId);
		}
		
		return theEmployee;
		
	}
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
		
	}
	@PutMapping("/employees")
	public Employee UpdateEmployee(@RequestBody Employee theEmployee) {
		
		employeeService.save(theEmployee);
		return theEmployee;
		
	}
	@DeleteMapping("/employee/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee tempEmployee=employeeService.findById(employeeId);
		if(tempEmployee==null) {
			throw new RuntimeException("Employee id not found "+employeeId);
		}
		employeeService.deleteById(employeeId);
		return "Delete employee id"+employeeId;
		
		
	}
	
	@GetMapping("/employee/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName)
	{
		return employeeService.searchByFirstName(firstName);
	}
	
	@GetMapping("/employee/sort")
	public List<Employee> sortByFirstName(@RequestParam(name="order") String order)
	{
		return employeeService.sortByFirstName(order);
	}
	
	

}
