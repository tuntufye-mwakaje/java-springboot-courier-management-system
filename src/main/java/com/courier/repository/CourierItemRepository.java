package com.courier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courier.model.CourierItem;

@Repository
public interface CourierItemRepository extends JpaRepository<CourierItem, Integer> {

}
