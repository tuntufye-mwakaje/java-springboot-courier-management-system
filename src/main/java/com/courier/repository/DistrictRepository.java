package com.courier.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.courier.model.District;




@Repository
public interface DistrictRepository extends JpaRepository<District, Long>{

	public List<District> getDistrictByStateId(Long id);
}
