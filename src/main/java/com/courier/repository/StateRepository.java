package com.courier.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.courier.model.State;


@Repository
public interface StateRepository extends JpaRepository<State, Long>{

//	@Query("From State where country_id = :id")
	List<State> getStateByCountryId(Long id);
	
}
