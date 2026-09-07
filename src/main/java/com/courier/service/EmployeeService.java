package com.courier.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courier.model.Employee;
import com.courier.repository.EmployeeRepository;



@Service
public class EmployeeService {

@Autowired 
EmployeeRepository employeerepo;



public void saveEmployee(Employee employee) {
	 employeerepo.save(employee);	
}
}
