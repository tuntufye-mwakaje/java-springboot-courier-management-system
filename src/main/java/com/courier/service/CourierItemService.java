package com.courier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courier.model.CourierItem;
import com.courier.repository.CourierItemRepository;

@Service
public class CourierItemService {
	@Autowired
	private CourierItemRepository courierItemRepository;
	
	public CourierItem getCourierById(Integer id) {
		return courierItemRepository.findById(id).get();
	}
	
	public List<CourierItem> getAllCourierItem(){
		return courierItemRepository.findAll();
	}
	
	public void saveCourier(CourierItem courrierItem) {
		courierItemRepository.save(courrierItem);
	}
	
	public void deleteCourierById(Integer id) {
		courierItemRepository.deleteById(id);
	}
	

}
