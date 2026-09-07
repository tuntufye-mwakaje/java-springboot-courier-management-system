package com.courier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.courier.model.CourierCustomer;

@Repository
public interface CourierCustomerRepository extends JpaRepository<CourierCustomer, Integer> {
	
	@Query("SELECT cc FROM CourierCustomer cc WHERE cc.mobNo = :mobNo")
	CourierCustomer findCourierCustomerByMobNo(String mobNo);
	
	//CourierCustomer findByMobNo(String mobNo);
}
