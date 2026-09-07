package com.courier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courier.model.State;
import com.courier.repository.StateRepository;

import jakarta.validation.Valid;



@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;

	public List<State> getAll() {

		return stateRepository.findAll();
	}

	public void saveState(@Valid State state) {

		stateRepository.save(state);
	}

	@SuppressWarnings("deprecation")
	public State update(Long id) {
		
		return stateRepository.getOne(id);
		
	}

	public void delete(Long id) {

		stateRepository.deleteById(id);
	}

	public List<State> getStateByCountryId(Long id) {

		return stateRepository.getStateByCountryId(id);
	}

	
	
}
