package com.courier.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class CourierConsumer {
	private String name;
	private String emailId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String pincode;
	public CourierConsumer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CourierConsumer(String name, String emailId, String addressLine1, String addressLine2, String city,
			String pincode) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.pincode = pincode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "CourierConsumer [name=" + name + ", emailId=" + emailId + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", city=" + city + ", pincode=" + pincode + "]";
	}
	
	

}
