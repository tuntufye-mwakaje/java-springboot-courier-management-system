package com.courier.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.courier.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


}
