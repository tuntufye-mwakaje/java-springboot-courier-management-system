package com.courier.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class CourierItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
//	@NotNull(message = "Required Field!")
	private Double length;
//	@NotNull(message = "Required Field!")
	private Double width;
//	@NotNull(message = "Required Field!")
	private Double height;
//	@NotNull(message = "Required Field!")
	private Double volumetricWeight;
//	@NotNull(message = "Required Field!")
	private Double weight;
//	@NotBlank(message = "Required Field!")
	private String fragile;

	@ManyToOne
	@JoinColumn(name = "courier_id")
	private Courier courier;
	public CourierItem() {
		super();

	}
	
	
	public CourierItem(Integer id, Double length, Double width, Double volumetricWeight, Double weight, String fragile,
			Courier courier) {
		super();
		this.id = id;
		this.length = length;
		this.width = width;
		this.volumetricWeight = volumetricWeight;
		this.weight = weight;
		this.fragile = fragile;
		this.courier = courier;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getVolumetricWeight() {
		return volumetricWeight;
	}
	public void setVolumetricWeight(Double volumetricWeight) {
		this.volumetricWeight = volumetricWeight;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getFragile() {
		return fragile;
	}
	public void setFragile(String fragile) {
		this.fragile = fragile;
	}
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	
	
	public Double getHeight() {
		return height;
	}


	public void setHeight(Double height) {
		this.height = height;
	}


	@Override
	public String toString() {
		return "CourierItem [id=" + id + ", length=" + length + ", width=" + width + ", height=" + height
				+ ", volumetricWeight=" + volumetricWeight + ", weight=" + weight + ", fragile=" + fragile
				+  "]";
	}
	
	
	
	
}
