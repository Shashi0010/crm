package com.durgasoft.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgasoft.crm.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	//Search By Name
	public List<Employee> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String firstName, String lastName);
}
