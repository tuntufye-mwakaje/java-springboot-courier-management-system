package com.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.courier.model.Country;
import com.courier.service.CountryService;

import jakarta.validation.Valid;

@Controller
//@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping("/country")
//	@ResponseBody
	public List<Country> showCountryForm(Model model) {
		
		List<Country> countryList = countryService.getAll();
		model.addAttribute("countryList", countryList);
		model.addAttribute("country", new Country());

		return countryList;
	//	return "Country";
	}

	@PostMapping("/saveCountry")
	public String saveCountry(
			@Valid @ModelAttribute("country") Country country,
			BindingResult bindingResult,
			Model model
			) {
		
		List<Country> countryList = countryService.getAll();
		model.addAttribute("countryList", countryList);
		
		if (bindingResult.hasErrors()) {
			return "Country";
		} else {
			countryService.saveCountry(country);
			return "redirect:/country";
		}

	}

	@GetMapping("/update")
	public String update(@ModelAttribute("id") Long id, Model model) {
		List<Country> countryList = countryService.getAll();
		model.addAttribute("countryList", countryList);
		Country country = countryService.getCountryById(id);
		model.addAttribute("country", country);

		return "Country";
	}

	@GetMapping("/delete")
	public String delete(Long id) {
		countryService.delete(id);
		return "redirect:/country";
	}

}
