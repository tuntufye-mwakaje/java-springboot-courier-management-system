package com.courier.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String empname;

	private String empemail;

	private String designation;

	private String empcontact;

	public Employee() {
		//default constructor
	}
	
	public Employee(Long id, String empname, String empemail, String designation, String empcontact) {
		super();
		this.id = id;
		this.empname = empname;
		this.empemail = empemail;
		this.designation = designation;
		this.empcontact = empcontact;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmpemail() {
		return empemail;
	}

	public void setEmpemail(String empemail) {
		this.empemail = empemail;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmpcontact() {
		return empcontact;
	}

	public void setEmpcontact(String empcontact) {
		this.empcontact = empcontact;
	}

	
}
