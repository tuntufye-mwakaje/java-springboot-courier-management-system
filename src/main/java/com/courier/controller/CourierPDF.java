package com.courier.controller;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.courier.model.Courier;
import com.courier.model.CourierItem;
import com.courier.model.District;          
import com.courier.service.CourierService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("deprecation")
@Controller
@RequestMapping("courier")
public class CourierPDF {
	

	CourierService courierService;
	
	public CourierPDF(CourierService courierService) {
		this.courierService = courierService;
	}
	
	
	private static Font fontbold1 = FontFactory.getFont("Times-Roman", 5, Font.BOLD);
	private static Font fontbold2 = FontFactory.getFont("Times-Roman", 7, Font.BOLD | Font.UNDERLINE);
	private static Font blueFont = new Font(Font.FontFamily.HELVETICA, 5, Font.NORMAL, BaseColor.BLACK); // value
	private static Font blueFont2 = new Font(Font.FontFamily.HELVETICA, 4, Font.NORMAL, BaseColor.BLACK); // value
	private static BaseColor myColor = WebColors.getRGBColor("#c9d69a");
	
	@GetMapping("/courierPdf/{id}")
	private void courierPdfGenerator(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Integer id) {
		
		
		System.out.println("ID : "+id);
		
		Courier courier = courierService.getCourierById(id);
		
		// use this id to get the object of courier/haulage
		
		response.setContentType("application/pdf");// step 1
		Document document = null;
		
		document = new Document(PageSize.A4, 36, 36, 50, 70);
				

		try {
			PdfWriter.getInstance(document, response.getOutputStream());
			
			
			document.open();
						
			Paragraph p = new Paragraph("WAYBILL/RECEIPT", fontbold2);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);			
			

			p = new Paragraph("                                         ");
			document.add(p);
			
			// Sender & Receiver Detial(s) Table
			// Start---------------------------------------------------------------------------->
			PdfPTable mainTable = new PdfPTable(50);
			mainTable.setWidthPercentage(100);
			mainTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			mainTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			mainTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			mainTable.getDefaultCell().setFixedHeight(70);

			// Sender Table
			PdfPTable senderTable = new PdfPTable(22);
			senderTable.setWidthPercentage(100);

			senderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			senderTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			senderTable.getDefaultCell().setFixedHeight(70);

			
			PdfPCell Cell = null;
			//Sender
			Cell = new PdfPCell(new Phrase("Sender Detail(s)", fontbold1));
			Cell.setBackgroundColor(myColor);
			Cell.setBorder(Rectangle.NO_BORDER);
			Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			Cell.setVerticalAlignment(Element.ALIGN_CENTER);
			Cell.setColspan(22);
			senderTable.addCell(Cell);
			
			// header
			Cell = new PdfPCell(new Phrase("Name", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);
			
			//Value
			Cell = new PdfPCell(new Phrase(courier.getSenderName(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Phone", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getSenderPhone(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Email Id", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getSenderEmailId(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("AddressLine 1", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getSenderAddressLine1(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("AddressLine 2", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getSenderAddressLine2(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Country", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			District senderDistrict = courier.getSenderDistrict();
			Cell = new PdfPCell(new Phrase(senderDistrict.getState().getCountry().getCountryName(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("State", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(senderDistrict.getState().getStateName(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("District", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(senderDistrict.getDistrictName(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("City/Town", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getSenderCity(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Pincode/Zipcode", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getSenderPincode(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Landmark", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getSenderLandmark(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			senderTable.addCell(Cell);

			// Blank Table
			PdfPTable BlankTable = new PdfPTable(6);
			BlankTable.setWidthPercentage(100);
			BlankTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			BlankTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			BlankTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			BlankTable.getDefaultCell().setFixedHeight(70);

			Cell = new PdfPCell();
			Cell.setColspan(6);
			Cell.setBorder(Rectangle.NO_BORDER);
			BlankTable.addCell(Cell);

			Cell = new PdfPCell();
			Cell.setColspan(6);
			Cell.setBorder(Rectangle.NO_BORDER);
			BlankTable.addCell(Cell);

			Cell = new PdfPCell();
			Cell.setColspan(6);
			Cell.setBorder(Rectangle.NO_BORDER);
			BlankTable.addCell(Cell);

			Cell = new PdfPCell();
			Cell.setColspan(6);
			Cell.setBorder(Rectangle.NO_BORDER);
			BlankTable.addCell(Cell);

			Cell = new PdfPCell();
			Cell.setColspan(6);
			Cell.setBorder(Rectangle.NO_BORDER);
			BlankTable.addCell(Cell);

			Cell = new PdfPCell();
			Cell.setColspan(6);
			Cell.setBorder(Rectangle.NO_BORDER);
			BlankTable.addCell(Cell);

			Cell = new PdfPCell();
			Cell.setColspan(6);
			Cell.setBorder(Rectangle.NO_BORDER);
			BlankTable.addCell(Cell);

			Cell = new PdfPCell();
			Cell.setColspan(6);
			Cell.setBorder(Rectangle.NO_BORDER);
			BlankTable.addCell(Cell);

			Cell = new PdfPCell();
			Cell.setColspan(6);
			Cell.setBorder(Rectangle.NO_BORDER);
			BlankTable.addCell(Cell);

			Cell = new PdfPCell();
			Cell.setColspan(6);
			Cell.setBorder(Rectangle.NO_BORDER);
			BlankTable.addCell(Cell);

			Cell = new PdfPCell();
			Cell.setColspan(6);
			Cell.setBorder(Rectangle.NO_BORDER);
			BlankTable.addCell(Cell);

			Cell = new PdfPCell();
			Cell.setColspan(6);
			Cell.setBorder(Rectangle.NO_BORDER);
			BlankTable.addCell(Cell);

			// Receiver Table
			PdfPTable receiverTable = new PdfPTable(22);
			receiverTable.setWidthPercentage(100);
			receiverTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			receiverTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			receiverTable.getDefaultCell().setFixedHeight(70);

			Cell = new PdfPCell(new Phrase("Receiver Detail(s)", fontbold1));
			Cell.setBackgroundColor(myColor);
			Cell.setBorder(Rectangle.NO_BORDER);
			Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			Cell.setVerticalAlignment(Element.ALIGN_CENTER);
			Cell.setColspan(22);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Name", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getReceiverName(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Phone", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getReceiverPhone(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Email Id", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getReceiverEmailId(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("AddressLine 1", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getReceiverAddressLine1(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("AddressLine 2", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getReceiverAddressLine2(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Country", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);
			
			District receiverDistrict = courier.getReceiverDistrict();
			Cell = new PdfPCell(new Phrase(receiverDistrict.getState().getCountry().getCountryName(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("State", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(receiverDistrict.getState().getStateName(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("District", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(receiverDistrict.getDistrictName(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("City/Town", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getReceiverCity(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Pincode/Zipcode", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getReceiverPincode(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Landmark", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getReceiverLandmark(), blueFont));
			Cell.setColspan(12);
			Cell.setBorderColor(myColor);
			receiverTable.addCell(Cell);

			Cell = new PdfPCell(senderTable);
			Cell.setBorder(Rectangle.NO_BORDER);
			Cell.setColspan(22);
			mainTable.addCell(Cell);

			Cell = new PdfPCell(BlankTable);
			Cell.setBorder(Rectangle.NO_BORDER);
			Cell.setColspan(6);
			mainTable.addCell(Cell);

			Cell = new PdfPCell(receiverTable);
			Cell.setBorder(Rectangle.NO_BORDER);
			Cell.setColspan(22);
			mainTable.addCell(Cell);

			document.add(mainTable);
			// Sender & Receiver Detial(s) Table
			// End---------------------------------------------------------------------------->

			p = new Paragraph("                                         ");
			document.add(p);

			// Shipment Detail(s) Table
			// Starts----------------------------------------------------------------------------------->
			PdfPTable Table3 = new PdfPTable(40);
			Table3.setWidthPercentage(100);
			Table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			Table3.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			Table3.getDefaultCell().setFixedHeight(70);
			// Table3.getDefaultCell().setBorder(Rectangle.NO_BORDER); does not work, dnt
			// know why :/

			Cell = new PdfPCell(new Phrase("Shipping Detail(s)", fontbold1));
			Cell.setBackgroundColor(myColor);
			Cell.setBorder(Rectangle.NO_BORDER);
			Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			Cell.setVerticalAlignment(Element.ALIGN_CENTER);
			Cell.setColspan(40);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Issued By", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			
			Cell = new PdfPCell(new Phrase("Rajibul Islam", blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("", blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);
			
			Cell = new PdfPCell(new Phrase("Booking Date", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getDate().toString(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Tracking Code", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getTrackingCode(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Booking Code", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getBookingCode(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("EWay Bill No", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getEwayBillNo(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Expected Delivery", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getExpectedDelivery().toString(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Courier Service", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);
		
			Cell = new PdfPCell(new Phrase(courier.getCourierService(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Consignment Description", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getDescription(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Declared Value", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Nill", blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Consignment Type", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getConsignmentType(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("No of Piece(s)", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase(""+courier.getNoOfPieces(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Courier Mode", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getCourierMode(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Hazardous Material ?", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase((courier.getHazardious()?"Yes":"No"), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Courier Center", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getCourierCenter(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Explanation", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getExplanation(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);
			
			
			Cell = new PdfPCell(new Phrase("Total Weight("+ courier.getWeightUnit() +")", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase(""+courier.getTotalWeight(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Total Distance("+courier.getDistanceUnit()+")", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			Cell = new PdfPCell(new Phrase(""+courier.getDistance(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table3.addCell(Cell);

			document.add(Table3);
			// Shipping Detail(s) Table
			// End---------------------------------------------------------------------------------->

			// Item(s) Table
				p = new Paragraph("                                         ");
				document.add(p);
				
				// Starts---------------------------------------------------------------------------------------->
				PdfPTable Table4 = new PdfPTable(51);
				Table4.setWidthPercentage(100);
				Table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Table4.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
				Table4.getDefaultCell().setFixedHeight(70);

				Cell = new PdfPCell(new Phrase("Item(s)", fontbold1));
				Cell.setBackgroundColor(myColor);
				Cell.setBorder(Rectangle.NO_BORDER);
				Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				Cell.setVerticalAlignment(Element.ALIGN_CENTER);
				Cell.setColspan(51);
				Table4.addCell(Cell);

				Cell = new PdfPCell(new Phrase("S.No.", fontbold1));
				Cell.setColspan(3);
				Cell.setBorderColor(myColor);
				Table4.addCell(Cell);

				Cell = new PdfPCell(new Phrase("Reference No.", fontbold1));
				Cell.setColspan(7);
				Cell.setBorderColor(myColor);
				Table4.addCell(Cell);

				Cell = new PdfPCell(new Phrase("Weight("+courier.getWeightUnit()+")", fontbold1));
				Cell.setColspan(6);
				Cell.setBorderColor(myColor);
				Table4.addCell(Cell);

				Cell = new PdfPCell(new Phrase("Volumetric Weight("+courier.getVolumeUnit()+")", fontbold1));
				Cell.setColspan(12);
				Cell.setBorderColor(myColor);
				Table4.addCell(Cell);

				Cell = new PdfPCell(new Phrase("Length("+courier.getLengthUnit()+")", fontbold1));
				Cell.setColspan(6);
				Cell.setBorderColor(myColor);
				Table4.addCell(Cell);

				Cell = new PdfPCell(new Phrase("Width(Nill)", fontbold1));
				Cell.setColspan(6);
				Cell.setBorderColor(myColor);
				Table4.addCell(Cell);

				Cell = new PdfPCell(new Phrase("Height(Nill)", fontbold1));
				Cell.setColspan(6);
				Cell.setBorderColor(myColor);
				Table4.addCell(Cell);

				Cell = new PdfPCell(new Phrase("Fragile", fontbold1));
				Cell.setColspan(5);
				Cell.setBorderColor(myColor);
				Table4.addCell(Cell);
				document.add(Table4);

				PdfPTable Table5 = new PdfPTable(51);
				
				Table5.setWidthPercentage(100);
				Table5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				Table5.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
				Table5.getDefaultCell().setFixedHeight(70);

				int sr_no= 1;
				for (CourierItem courierItem : courier.getCourierItems()) {
					
					Cell = new PdfPCell(new Phrase((sr_no++) + "", blueFont));
					Cell.setColspan(3);
					Cell.setBorderColor(myColor);
					Table5.addCell(Cell);

					Cell = new PdfPCell(new Phrase("Nill", blueFont));
					Cell.setColspan(7);
					Cell.setBorderColor(myColor);
					Table5.addCell(Cell);

					Cell = new PdfPCell(new Phrase(""+courierItem.getWeight(), blueFont));
					Cell.setColspan(6);
					Cell.setBorderColor(myColor);
					Table5.addCell(Cell);

					Cell = new PdfPCell(new Phrase(""+courierItem.getVolumetricWeight(), blueFont));
					Cell.setColspan(12);
					Cell.setBorderColor(myColor);
					Table5.addCell(Cell);

					Cell = new PdfPCell(new Phrase(""+courierItem.getLength(), blueFont));
					Cell.setColspan(6);
					Cell.setBorderColor(myColor);
					Table5.addCell(Cell);

					Cell = new PdfPCell(new Phrase(""+courierItem.getWidth(), blueFont));
					Cell.setColspan(6);
					Cell.setBorderColor(myColor);
					Table5.addCell(Cell);

					Cell = new PdfPCell(new Phrase(""+courierItem.getHeight(), blueFont));
					Cell.setColspan(6);
					Cell.setBorderColor(myColor);
					Table5.addCell(Cell);

					Cell = new PdfPCell(new Phrase(courierItem.getFragile(), blueFont));
					Cell.setColspan(5);
					Cell.setBorderColor(myColor);
					Table5.addCell(Cell);
				}
				document.add(Table5);
						
			// Item(s) Table
			// Ends---------------------------------------------------------------------------------------->

			p = new Paragraph("                                         ");
			document.add(p);

			// Payment Detail(s) Table
			// Starts---------------------------------------------------------------------------------------->

			PdfPTable Table6 = new PdfPTable(40);
			Table6.setWidthPercentage(100);
			Table6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			Table6.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			Table6.getDefaultCell().setFixedHeight(70);

			Cell = new PdfPCell(new Phrase("Charges Detail(s)", fontbold1));
			Cell.setBackgroundColor(myColor);
			Cell.setBorder(Rectangle.NO_BORDER);
			Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			Cell.setVerticalAlignment(Element.ALIGN_CENTER);
			Cell.setColspan(40);
			Table6.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Courier Charges", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table6.addCell(Cell);

			Cell = new PdfPCell(new Phrase(""+courier.getCharges(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table6.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Tax Type", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table6.addCell(Cell);

			Cell = new PdfPCell(new Phrase(courier.getTaxType(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table6.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Tax Amount", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table6.addCell(Cell);
			
			Double taxAmount = (courier.getTaxPercentage()/100) * courier.getCharges();
			Double finalCharges = courier.getCharges()+taxAmount;
			
			Cell = new PdfPCell(new Phrase(""+taxAmount, blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table6.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Tax %", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table6.addCell(Cell);
			
			
			

			Cell = new PdfPCell(new Phrase(""+courier.getTaxPercentage(), blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table6.addCell(Cell);
			
			
			Double discountAmount = 0.00;
			String discountPercentage = "";
			if(courier.getDiscountType().equalsIgnoreCase("percentage")) {
				discountAmount = (courier.getDiscount()/100) * courier.getCharges();
			}else {
				discountAmount = (courier.getDiscount()==null) ? 0.00:courier.getDiscount();
			}
			
			
			
			if(courier.getDiscountType().equalsIgnoreCase("percentage")) {
				discountPercentage = "" + courier.getDiscount();
			}else {
				discountPercentage = "" + (discountAmount/finalCharges)*100;
			}
			
			//Update Final Charges
			finalCharges-=discountAmount;
			
			Cell = new PdfPCell(new Phrase("Discount Amount ("+ discountPercentage +"%)", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table6.addCell(Cell);
			
			
		
			
			

			Cell = new PdfPCell(new Phrase(""+discountAmount, blueFont));
			Cell.setColspan(10);
			//Cell.setBorder(Rectangle.NO_BORDER);
			Cell.setBorderColor(myColor);
			Table6.addCell(Cell);
			
			
			
			Cell = new PdfPCell(new Phrase("Final Charges / Paid Amount", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table6.addCell(Cell);

			Cell = new PdfPCell(new Phrase(""+finalCharges, blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table6.addCell(Cell);

			Cell = new PdfPCell(new Phrase("Final Charges / Paid Amount", fontbold1));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table6.addCell(Cell);

			Cell = new PdfPCell(new Phrase(""+finalCharges, blueFont));
			Cell.setColspan(10);
			Cell.setBorderColor(myColor);
			Table6.addCell(Cell);
			document.add(Table6);

					
			p = new Paragraph("STANDARD TERMS AND CONDITIONS OF CARRIAGE", fontbold2);
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			
			p = new Paragraph(" 											", fontbold2);
			
			document.add(p);
									
			p = new Paragraph("DECLARATION: This WB is non-negotiable. Carriage on this instrument is subject to the QuickDispatch.COURIER SERVICES terms and conditions hereinafter set forth and which have been read and agreed with by the consignor. The liability of QuickDispatch.COURIER SERVICE if its servants, agent or privies for any loss or damages is limited to the agreements of the terms and conditions of carriage under this instrument. The consignor acknowledges, having carefully read the instructions, declarations, terms and condition of this contract and also agrees that the information supplied  written are correct in every material detail and particular. ", blueFont2);
			document.add(p);
						
			
			p = new Paragraph("IMPORTANT NOTICE: By the conditions set out in these presents CCL COURIER SERVICES and its servant, agent and privies  and firstly not to be liable at all for certain losses and damages and secondly wheresoever they are liable the amount of liability is strictly limited as stated in conditions (B) (5) here under; ", blueFont2);
			document.add(p);
			
			p = new Paragraph("                                         ");
			document.add(p);
			
			p = new Paragraph("(A) OUR TERMS AND CONDITIONS ", blueFont2);
			document.add(p);
						
			
			p = new Paragraph("1. OUR CONTRACT WITH YOU: These are terms and conditions governing the contract between the sender of the shipment (or consignor) and ABC COURIER SDERVICES. When you give us your shipment you accept our terms and conditions of carriage both for yourself and for anyone else who has interest in the shipment. Our terms and conditions protect anyone who we may contract to collect, transact or deliver your shipment. No other employees or anyone else has authority to change or misconstrue or misinterpret these terms and conditions or make any alternative promise on our behalf. ", blueFont2);
			document.add(p);
			
			p = new Paragraph("2. WHAT SHIPMENT MEANS: Shipment means all documents /parcels/goods that come under our delivery document. You agree and certify that shipment details are complete and accurate as written on the waybill.", blueFont2);
			document.add(p);
			
			p = new Paragraph("3. INSPECTION AND CHARGING: All goods for shipment must be opened and inspected in the presence of the sender and charged at the prevailing tariff at CCL COURIER SERVICES.", blueFont2);
			document.add(p);
			
			p = new Paragraph("(B)", blueFont2);
			document.add(p);
			
			p = new Paragraph("4. CLAIMS: Claim for lost or damaged shipment must be made by the shipper in writing and received at the Head Office of CCL. COURIER SERVICES within 7 days from the date the shipment is supposed to be received at the destination. ", blueFont2);
			document.add(p);
			
			p = new Paragraph("5.  WHAT WE ARE LIABLE FOR EXTENT OF OUR LAIBILITY : (Subjects to clauses a(1) above) and in respect of any one shipment our liability for any loss or damage however occasioned is limited to 20% of the declared value of the shipment evidenced with relevant and appropriate purchase receipts or subject clause 6 whichever is higher otherwise our liability is limited to ₦500. ", blueFont2);
			document.add(p);
			
			p = new Paragraph("6. INSURANCE: All shipment in our care are covered by insurance. In the event of claim on loss or damage to any shipment our extent of liability shall be limited to item 5 of our condition of carriage. We shall not be liable to and our insurance shall not cover consequential damages or loss or damage caused by transport delays including road accidents, armed robbery, riots and strikers or occasioned by government legislation etc. ", blueFont2);
			document.add(p);
			
			
			p = new Paragraph("(C) WHAT WE ARE NOT LIABLE FOR", blueFont2);
			document.add(p);
			
			p = new Paragraph("7. DELAYED SHIPMENTS: We shall make every reasonable effort to deliver your shipment according to our normal delivery schedules. We are not liable for delays or loses occasioned beyond our control e.g. Vehicle breakdown, chaotic traffic, congestion, armed robbery attack, unfavorable weather conditions, fire outbreak of any kind or nature as contained in Clause C8 and C9. ", blueFont2);
			document.add(p);
			
			p = new Paragraph("8. CIRCUMSTANCES BEYOND OUR CONTROL: We are not liable of a shipment if lost, damaged or mis-delivered because of circumstances beyond our control which include but not limited to:", blueFont2);
			document.add(p);
			
			p = new Paragraph("*	Fire Disaster of any kind and nature.", blueFont2);
			document.add(p);
			
			p = new Paragraph("*	Act of God e.g. Earthquake, cyclone, storm or flood etc. ", blueFont2);
			document.add(p);
			
			p = new Paragraph("*	Force majeure e.g. War, plane crash, embargo or any defect characteristic with the nature of this shipment even  when known to us and we accepted it. ", blueFont2);
			document.add(p);
			
			p = new Paragraph("*	Any action, inaction, commission of omission by anyone outside CCL COURIER SERVICES i.e. Landlords, co-tenants or their servants’ agents and privies, The sender of the shipment, The receiver, Any interested third party, Customs or any other government body, their officials, the postal services, other carrier of third party who we may contact to serve locations that we do not serve directly. ", blueFont2);
			document.add(p);
			
			p = new Paragraph("Furthermore, we are not liable even if the sender did not ask for or know about a third party arrangement. We are also not liable for electrical or magnetic damage or erasure of electronic or photographic images or recordings.", blueFont2);
			document.add(p);
			
			p = new Paragraph("9. CONSEQUENTIAL DAMAGES: We are not liable for the following, whether they arise on contract or through any form of civil action, including negligence and carelessness even if they are our fault indirectly : ", blueFont2);
			document.add(p);
			
			p = new Paragraph("*	Consequential or special damage or loss ", blueFont2);
			document.add(p);
			
			p = new Paragraph("*	Other indirect loss or losses ", blueFont2);
			document.add(p);
			
			
			p = new Paragraph("*	Breach of other contracts. ", blueFont2);
			document.add(p);
			
			p = new Paragraph("Consequential damage or loss includes but not limited to loss of income, profits, interests, markets or use of content of shipment. ", blueFont2);
			document.add(p);
			
			p = new Paragraph("(D) OTHER CONDITIONS: ", blueFont2);
			document.add(p);
			
			p = new Paragraph("10. These terms and conditions of carriage shall be governed by the law of the Federal Republic of Nigeria and subject to the court of competent jurisdiction .", blueFont2);
			document.add(p);
			
			p = new Paragraph("11. Any shipment on demurrages shall be governed by Section 9 of the Waybill overleaf.", blueFont2);
			document.add(p);
			
						
			document.close();
						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
