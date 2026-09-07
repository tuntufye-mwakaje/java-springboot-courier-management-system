package com.courier.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.courier.model.Country;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long>{

	@Query("FROM Country c WHERE c.countryName = :countryName")
    public Country existCountry(String countryName);

}
