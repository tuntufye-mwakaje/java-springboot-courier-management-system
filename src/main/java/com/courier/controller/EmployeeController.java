package com.courier.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.courier.model.Employee;
import com.courier.service.EmployeeService;


@Controller
@RequestMapping("/addemployee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeservice;

	
	@GetMapping("employee")
	public String addemplyeeform(Model model) {
		
		model.addAttribute("employee", new Employee());
		return "AddEmployee";
	}

	@PostMapping("addemployee")
	public String save(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
		

	
		employeeservice.saveEmployee(employee);
			


        
        return "redirect:/addemployee";
    }

}