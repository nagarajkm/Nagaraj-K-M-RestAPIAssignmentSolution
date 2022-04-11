package com.EmployeeServices.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.EmployeeServices.dao.UserRepository;
import com.EmployeeServices.entity.User;
import com.EmployeeServices.security.MyUserDeatils;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepository.getUserByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("could not find user");
		}
		return new MyUserDeatils(user);
		
		
	} 

}
