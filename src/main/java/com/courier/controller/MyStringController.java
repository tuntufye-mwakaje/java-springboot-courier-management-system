package com.courier.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.courier.model.MyString;

@Controller
public class MyStringController {
	
	private static MyString mainyString = new MyString();
	
	@GetMapping("/string-form")
	public String stringForm(Model model) {
		MyString myString = new MyString();
		myString.getStrings().add("");
		
		model.addAttribute("myString", myString);
		model.addAttribute("mainMyString", mainyString);
		
		return "StringForm";
	}
	
	@PostMapping("/save-string")
	public String saveForm(@ModelAttribute MyString myString) {
		System.out.println(myString.getStrings());
		MyStringController.mainyString.getStrings().addAll(myString.getStrings());
		return "redirect:/string-form";
	}
	
	
	
	
	

}
