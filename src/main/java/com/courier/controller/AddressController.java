package com.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.courier.model.Country;
import com.courier.model.District;
import com.courier.model.State;
import com.courier.service.CountryService;
import com.courier.service.DistrictService;
import com.courier.service.StateService;



@Controller
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private CountryService countryService;

	@Autowired
	private StateService stateService;

	@Autowired
	private DistrictService districtService;
	
	@GetMapping("")
	public String getAddressPage(Model model) {
		List<Country> countryList = countryService.getAll();
		model.addAttribute("countryList", countryList);

		return "Address";
	}
	
	@GetMapping("/getValuesById")
	public String getValuesBasedOnAction(@RequestParam("id") Long id, @RequestParam("action") String action, Model model) {
		List<State> specificStateList = stateService.getStateByCountryId(id);
		List<District> specificDistrictList = districtService.getDistrictByStateId(id);
		
		model.addAttribute("action", action);
		model.addAttribute("specificStateList", specificStateList);
		model.addAttribute("specificDistrictList", specificDistrictList);
		
		System.out.println(specificDistrictList);
		System.out.println(specificStateList);
		System.out.println(action);
		System.out.println(id);
		
		return "ajax";
	}
	

}
