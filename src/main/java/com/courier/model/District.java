package com.courier.model;



//import java.util.ArrayList;
//import java.util.List;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;



@Entity
public class District {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty(message = "Field can't be empty!")
	private String districtName;
	
	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;
	
	/*
	 * @OneToMany(mappedBy = "district", targetEntity = Employee.class, cascade =
	 * CascadeType.ALL) List<Employee> employeeList=new ArrayList<>();
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "District [id=" + id + ", districtName=" + districtName + "]";
	}

	
	public boolean isEmpty() {
		if(id!=null) {
			return true;
		}
		
		return false;
	}
	
//	public List<Employee> getEmployeeList() {
//		return employeeList;
//	}
//
//	public void setEmployeeList(List<Employee> employeeList) {
//		this.employeeList = employeeList;
//	}

	
	
	
}

