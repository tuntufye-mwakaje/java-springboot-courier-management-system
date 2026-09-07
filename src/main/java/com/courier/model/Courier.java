package com.courier.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Courier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String bookingCode;
	@NotNull(message = "Date cannot be Empty!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	@NotBlank(message = "Required Field!")
	private String weightUnit;
	@NotBlank(message = "Required Field!")
	private String volumeUnit;
	@NotBlank(message = "Required Field!")
	private String lengthUnit;
	@NotBlank(message = "Required Field!")
	private String distanceUnit;

	@NotBlank(message = "Required Field!")
	private String courierMode;
//	@NotBlank(message = "Required Field!")
	private String ewayBillNo;
	@NotNull(message = "Required Field!")
	private Boolean hazardious;
	@NotBlank(message = "Required Field!")
	private String explanation;
	@NotBlank(message = "Required Field!")
	private String consignmentType;
	@NotNull(message = "Required Field!")
	private Integer noOfPieces;

	// should check the existence
//	private String courierContent;

	@NotBlank(message = "Required Field!")
	private String courierCenter;
//	@NotBlank(message = "Required Field!")
	private String description;
	@NotNull(message = "Required Field!")
	private Double distance;

	@NotNull(message = "Expected Delivery Date cannot be Empty!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expectedDelivery;
	@NotBlank(message = "Required Field!")
	private String courierService;

//	@NotNull(message = "Required Field!")
	private Double totalWeight;
	@NotNull(message = "Required Field!")
	private Double charges;
	@NotNull(message = "Required Field!")
	private Double finalCharges;
	@NotBlank(message = "Required Field!")
	private String taxType;
//	@NotNull(message = "Required Field!")
	private Double taxPercentage;
	@NotBlank(message = "Required Field!")
	private String discountType; // % or amount;
	@NotNull(message = "Required Field!")
	private Double discount; // % or amount;

	@Valid
	@OneToMany(mappedBy = "courier", cascade = CascadeType.ALL)
	private List<CourierItem> courierItems;
	// Association 'com.courier.model.Courier.customer'
	// targets an unknown entity named 'com.courier.model.CourierCustomer'

	@ManyToOne
	@JoinColumn(name = "courierCustomer_id")
	private CourierCustomer customer;

	// Sender Information
	@NotBlank(message = "Required Field!")
	private String senderName;
	@NotBlank(message = "Required Field!")
	private String senderPhone;
	@NotBlank(message = "Required Field!")
	private String senderEmailId;
	@NotBlank(message = "Required Field!")
	private String senderAddressLine1;
//	@NotBlank(message = "Required Field!")
	private String senderAddressLine2;
	@NotBlank(message = "Required Field!")
	private String senderCity;
	@NotBlank(message = "Required Field!")
	private String senderPincode;
	@NotBlank(message = "Required Field!")
	private String senderLandmark;

	@ManyToOne
	@JoinColumn(name = "sender_district_id")
	private District senderDistrict;

	// receiver Information
	@NotBlank(message = "Required Field!")
	private String receiverName;
	@NotBlank(message = "Required Field!")
	private String receiverPhone;
	@NotBlank(message = "Required Field!")
	private String receiverEmailId;
	@NotBlank(message = "Required Field!")
	private String receiverAddressLine1;
//	@NotBlank(message = "Required Field!")
	private String receiverAddressLine2;
	@NotBlank(message = "Required Field!")
	private String receiverCity;
	@NotBlank(message = "Required Field!")
	private String receiverPincode;
	@NotBlank(message = "Required Field!")
	private String receiverLandmark;
	
	@ManyToOne
	@JoinColumn(name = "receiver_district_id")
	private District receiverDistrict;
	
	private Integer rowCounter;
	
	private String courierStatus;
	private String trackingCode;
	
	

	public Courier() {
		super();
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookingCode() {
		return bookingCode;
	}

	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	public String getVolumeUnit() {
		return volumeUnit;
	}

	public void setVolumeUnit(String volumeUnit) {
		this.volumeUnit = volumeUnit;
	}

	public String getLengthUnit() {
		return lengthUnit;
	}

	public void setLengthUnit(String lengthUnit) {
		this.lengthUnit = lengthUnit;
	}

	public String getDistanceUnit() {
		return distanceUnit;
	}

	public void setDistanceUnit(String distanceUnit) {
		this.distanceUnit = distanceUnit;
	}

	public String getCourierMode() {
		return courierMode;
	}

	public void setCourierMode(String courierMode) {
		this.courierMode = courierMode;
	}

	public String getEwayBillNo() {
		return ewayBillNo;
	}

	public void setEwayBillNo(String ewayBillNo) {
		this.ewayBillNo = ewayBillNo;
	}

	public Boolean getHazardious() {
		return hazardious;
	}

	public void setHazardious(Boolean hazardious) {
		this.hazardious = hazardious;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getConsignmentType() {
		return consignmentType;
	}

	public void setConsignmentType(String consignmentType) {
		this.consignmentType = consignmentType;
	}

	public Integer getNoOfPieces() {
		return noOfPieces;
	}

	public void setNoOfPieces(Integer noOfPieces) {
		this.noOfPieces = noOfPieces;
	}

//	public String getCourierContent() {
//		return courierContent;
//	}
//
//	public void setCourierContent(String courierContent) {
//		this.courierContent = courierContent;
//	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public LocalDate getExpectedDelivery() {
		return expectedDelivery;
	}

	public void setExpectedDelivery(LocalDate expectedDelivery) {
		this.expectedDelivery = expectedDelivery;
	}

	public String getCourierService() {
		return courierService;
	}

	public void setCourierService(String courierService) {
		this.courierService = courierService;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Double getCharges() {
		return charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}

	public Double getFinalCharges() {
		return finalCharges;
	}

	public void setFinalCharges(Double finalCharges) {
		this.finalCharges = finalCharges;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public Double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public List<CourierItem> getCourierItems() {
		return courierItems;
	}

	public void setCourierItems(List<CourierItem> courierItems) {
		this.courierItems = courierItems;
	}

	public CourierCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(CourierCustomer customer) {
		this.customer = customer;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public String getSenderEmailId() {
		return senderEmailId;
	}

	public void setSenderEmailId(String senderEmailId) {
		this.senderEmailId = senderEmailId;
	}

	public String getSenderAddressLine1() {
		return senderAddressLine1;
	}

	public void setSenderAddressLine1(String senderAddressLine1) {
		this.senderAddressLine1 = senderAddressLine1;
	}

	public String getSenderAddressLine2() {
		return senderAddressLine2;
	}

	public void setSenderAddressLine2(String senderAddressLine2) {
		this.senderAddressLine2 = senderAddressLine2;
	}

	public String getSenderCity() {
		return senderCity;
	}

	public void setSenderCity(String senderCity) {
		this.senderCity = senderCity;
	}

	public String getSenderPincode() {
		return senderPincode;
	}

	public void setSenderPincode(String senderPincode) {
		this.senderPincode = senderPincode;
	}

	public District getSenderDistrict() {
		return senderDistrict;
	}

	public void setSenderDistrict(District senderDistrict) {
		this.senderDistrict = senderDistrict;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiverEmailId() {
		return receiverEmailId;
	}

	public void setReceiverEmailId(String receiverEmailId) {
		this.receiverEmailId = receiverEmailId;
	}

	public String getReceiverAddressLine1() {
		return receiverAddressLine1;
	}

	public void setReceiverAddressLine1(String receiverAddressLine1) {
		this.receiverAddressLine1 = receiverAddressLine1;
	}

	public String getReceiverAddressLine2() {
		return receiverAddressLine2;
	}

	public void setReceiverAddressLine2(String receiverAddressLine2) {
		this.receiverAddressLine2 = receiverAddressLine2;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverPincode() {
		return receiverPincode;
	}

	public void setReceiverPincode(String receiverPincode) {
		this.receiverPincode = receiverPincode;
	}

	public District getReceiverDistrict() {
		return receiverDistrict;
	}

	public void setReceiverDistrict(District receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public String getSenderLandmark() {
		return senderLandmark;
	}

	public void setSenderLandmark(String senderLandmark) {
		this.senderLandmark = senderLandmark;
	}

	public String getReceiverLandmark() {
		return receiverLandmark;
	}

	public void setReceiverLandmark(String receiverLandmark) {
		this.receiverLandmark = receiverLandmark;
	}

	public String getCourierCenter() {
		return courierCenter;
	}

	public void setCourierCenter(String courierCenter) {
		this.courierCenter = courierCenter;
	}
	
	

	public Integer getRowCounter() {
		return rowCounter;
	}

	public void setRowCounter(Integer rowCounter) {
		this.rowCounter = rowCounter;
	}

	public String getTrackingCode() {
		return trackingCode;
	}

	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}

	public String getCourierStatus() {
		return courierStatus;
	}

	public void setCourierStatus(String courierStatus) {
		this.courierStatus = courierStatus;
	}
	
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	
	
	
	
	@Override
	public String toString() {
		return "Courier [id=" + id + ", bookingCode=" + bookingCode + ", date=" + date + ", weightUnit=" + weightUnit
				+ ", volumeUnit=" + volumeUnit + ", lengthUnit=" + lengthUnit + ", distanceUnit=" + distanceUnit
				+ ", courierMode=" + courierMode + ", ewayBillNo=" + ewayBillNo + ", hazardious=" + hazardious
				+ ", explaination=" + explanation + ", consignmentType=" + consignmentType + ", noOfPieces="
				+ noOfPieces + ", courierCenter=" + courierCenter + ", description=" + description + ", distance="
				+ distance + ", expectedDelivery=" + expectedDelivery + ", courierService=" + courierService
				+ ", totalWeight=" + totalWeight + ", charges=" + charges + ", finalCharges=" + finalCharges
				+ ", taxType=" + taxType + ", taxPercentage=" + taxPercentage + ", discountType=" + discountType
				+ ", courierItems=" + courierItems + ", customer=" + customer + ", senderName=" + senderName
				+ ", senderPhone=" + senderPhone + ", senderEmailId=" + senderEmailId + ", senderAddressLine1="
				+ senderAddressLine1 + ", senderAddressLine2=" + senderAddressLine2 + ", senderCity=" + senderCity
				+ ", senderPincode=" + senderPincode + ", senderLandmark=" + senderLandmark + ", senderDistrict="
				+ senderDistrict + ", receiverName=" + receiverName + ", receiverPhone=" + receiverPhone
				+ ", receiverEmailId=" + receiverEmailId + ", receiverAddressLine1=" + receiverAddressLine1
				+ ", receiverAddressLine2=" + receiverAddressLine2 + ", receiverCity=" + receiverCity
				+ ", receiverPincode=" + receiverPincode + ", receiverLandmark=" + receiverLandmark
				+ ", receiverDistrict=" + receiverDistrict + "]";
	}
	
	

}
