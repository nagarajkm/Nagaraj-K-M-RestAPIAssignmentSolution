package com.EmployeeServices.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EmployeeServices.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
