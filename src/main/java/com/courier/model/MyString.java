package com.courier.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MyString {
	
	private List<String> strings = new ArrayList<>();

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

	@Override
	public String toString() {
		return "MyString [strings=" + strings + "]";
	}
	
	
	
}
