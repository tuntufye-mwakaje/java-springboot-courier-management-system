package com.courier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.courier.model.Registation;

public interface RegistrationRepository extends JpaRepository<Registation, Long> {

	@Query("from Registation where email=:username and password=:password")
	List<Registation> CheckLoggedUser(String username, String password);
}
