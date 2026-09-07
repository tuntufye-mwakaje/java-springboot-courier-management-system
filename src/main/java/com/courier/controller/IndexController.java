package com.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.courier.model.Courier;
import com.courier.model.Registation;
import com.courier.service.CourierService;

@Controller
public class IndexController {

	@Autowired
	private CourierService courierService;

	@Autowired
	private WebRequest webRequest;

	                                     
	@GetMapping("/Dashboard")
	public String Dashboard(Model model) {

		
		Registation sessionUser= (Registation) webRequest.getAttribute("user", RequestAttributes.SCOPE_SESSION);
		
		if (sessionUser!=null) {
			model.addAttribute("sessionUser", sessionUser);
			List<Courier> courierList = courierService.findAll();
			System.out.println("size : " + courierList.size());
			model.addAttribute("ListSize", courierList.size()); // 2
			model.addAttribute("courierList", courierList);

			Double totalCharges = 0.00;

			for (Courier courier : courierList) {

				totalCharges += courier.getCharges();
			}
			model.addAttribute("totalCharges", totalCharges);

			return "Dashboard";
		} 
		else {
			return "redirect:/courier/login";
		}
		
		
	}

}
