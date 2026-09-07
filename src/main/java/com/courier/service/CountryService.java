package com.courier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courier.model.Country;
import com.courier.model.Registation;
import com.courier.repository.CountryRepository;
import com.courier.repository.RegistrationRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private RegistrationRepository registrationRepository;

	public CountryService(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	public List<Country> getAll() {
		return countryRepository.findAll();
	}

	public void saveCountry(Country country) {
		countryRepository.save(country);
	}

	public void delete(Long id) {
		countryRepository.deleteById(id);

	}

	public Country getCountryById(Long id) {
		return countryRepository.findById(id).get();
	}

	public boolean existCountry(Country country) {

		if (countryRepository.existCountry(country.getCountryName()) != null) {

			return true;
		}
		return false;
	}

	public void saveRegistrationForm(Registation registation) {

		registrationRepository.save(registation);
	}


}
