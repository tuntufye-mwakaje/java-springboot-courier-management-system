package com.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.courier.model.Country;
import com.courier.model.District;
import com.courier.model.State;
import com.courier.service.CountryService;
import com.courier.service.DistrictService;
import com.courier.service.StateService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("district")
public class DistrictController {

	@Autowired
	private CountryService countryService;

	@Autowired
	private StateService stateService;

	@Autowired
	private DistrictService districtService;

	@GetMapping("district")
	public String getAll(Model model) {

		List<District> districtList = districtService.getAll();
		model.addAttribute("districtList", districtList);
		model.addAttribute("district", new District());

//		List<State> stateList = stateService.getAll();
//		model.addAttribute("stateList", stateList);

		List<Country> countryList = countryService.getAll();
		model.addAttribute("countryList", countryList);

		return "District";

	}

	@PostMapping("saveDistrict")
	public String save(
			@Valid @ModelAttribute("district") District district, 
			BindingResult bindingResult, 
			Model model) {

		
//		List<State> stateList = stateService.getAll();
		

		if (district.getState().getCountry().getId() == null) {
			
			bindingResult.rejectValue("state.country.id", null, "please select country ");

		}
		if (district.getState().getId() == null) {
			bindingResult.rejectValue("state.id", null, "please select state");
		}

		if (bindingResult.hasErrors()) {
			List<District> districtList = districtService.getAll();
			List<Country> countryList = countryService.getAll();
			
			System.out.println("3");
			if (district.getState().getCountry().getId() != null) {
				List<State> stateList = stateService.getStateByCountryId(district.getState().getCountry().getId());
				model.addAttribute("stateList", stateList);
				model.addAttribute("district", district);
				System.out.println(district.getState().getCountry().getId());
			}
			
			model.addAttribute("districtList", districtList);
			model.addAttribute("countryList", countryList);
			
			return "District";

		} else {
			districtService.saveDistrict(district);
			return "redirect:district";

		}

	}

	@GetMapping("update")
	public String update(Model model, @ModelAttribute("id") Long id) {

		List<District> districtList = districtService.getAll();
		model.addAttribute("districtList", districtList);

		List<State> stateList = stateService.getAll();
		model.addAttribute("stateList", stateList);

		List<Country> countryList = countryService.getAll();
		model.addAttribute("countryList", countryList);

		District district = districtService.update(id);
		model.addAttribute("district", district);

		return "District";

	}

	@GetMapping("delete")
	public String delete(Long id) {

		districtService.delete(id);
		return "redirect:district";
	}

	@GetMapping("getStateByCountryId")
	public String getStateByCountryId(@RequestParam("id") Long id, @RequestParam("action") String action, Model model) {

		List<State> stateList = stateService.getStateByCountryId(id);

		model.addAttribute("specificStateList", stateList);
		model.addAttribute("action", action);
//		System.out.println("Hello :"+stateList.size());
		return "ajax";

	}

}
