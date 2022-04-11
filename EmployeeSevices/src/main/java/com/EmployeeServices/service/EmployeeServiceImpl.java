package com.EmployeeServices.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.EmployeeServices.dao.EmployeeRespository;
import com.EmployeeServices.dao.RoleRepository;
import com.EmployeeServices.dao.UserRepository;
import com.EmployeeServices.entity.Employee;
import com.EmployeeServices.entity.Role;
import com.EmployeeServices.entity.User;



@Repository
public class EmployeeServiceImpl implements EmployeeService {
	
private EmployeeRespository employeeRespository;

 @Autowired
 RoleRepository roleRepository;
 @Autowired
 UserRepository userRepository;
 @Autowired
 BCryptPasswordEncoder bcryptEncoder;
 
 @Autowired
 public EmployeeServiceImpl(EmployeeRespository theEmployeeRepository) {
	 
	 employeeRespository=theEmployeeRepository;
	 
 }

 
@Override
 public List<Employee> findAll(){
	 
	 return employeeRespository.findAll();
 }

@Override
public Employee findById(int theId) {
	Optional<Employee> result=employeeRespository.findById(theId);
	Employee theEmployee=null;
	if(result.isPresent()) {
		theEmployee=result.get();
	}else
	{
		throw new RuntimeException("Did not find the employee id "+theId);
	}
	return theEmployee;
}

@Override
public void save(Employee theEmployee) {
	employeeRespository.save(theEmployee);
}


@Override
public void deleteById(int theId) {
	employeeRespository.deleteById(theId);
}


@Override
public List<Employee> searchByFirstName(String firstName){
	return employeeRespository.findByFirstNameContainsAllIgnoreCase(firstName);
}



@Override
public List<Employee> sortByFirstName(String order){
	if(order.equals("desc"))
		return employeeRespository.findAllByOrderByFirstNameAsc();
	else
		return employeeRespository.findAllByOrderByFirstNameDesc();
}




@Override
public User saveUser(User user) {
	user.setPassword(bcryptEncoder.encode(user.getPassword()));
	return userRepository.save(user);
}



@Override
public Role saveRole(Role role) {
	return roleRepository.save(role);
}

}
