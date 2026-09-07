package com.courier.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.courier.model.Courier;
import com.courier.model.CourierItem;
import com.courier.model.Registation;
import com.courier.service.CountryService;
import com.courier.service.CourierService;
import com.courier.service.DistrictService;
import com.courier.service.StateService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/courier")
public class CourierController {

	@Autowired
	CountryService countryService;

	@Autowired
	CourierService courierService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	WebRequest webRequest;
	

	@GetMapping("/courier-form")
	public String showCourierForm(Model model) {

		model.addAttribute("countryList", countryService.getAll());

		model.addAttribute("saveReceiver", "true");
		model.addAttribute("dontUseWeights", false);
//		String message="Courier Booked Successfully !";
//		model.addAttribute("positiveMessage", message);
//		model.addAttribute("negativeMessage", message);

		Courier courier = new Courier();
		courier.setCourierItems(new ArrayList<>());
		courier.getCourierItems().add(new CourierItem());
		model.addAttribute("courier", courier);

		return "CourierForm";
	}

	@PostMapping("/saveNewCourier")
	public String saveCurier(@Valid @ModelAttribute("courier") Courier courier, BindingResult bindingResult,
			@RequestParam("saveReceiver") String saveReceiver,
			@RequestParam(value = "dontUseWeights", defaultValue = "false") boolean dontUseWeights, Model model) {

		if (courier.getSenderDistrict().getId() == null) {
			bindingResult.rejectValue("senderDistrict.id", null, "Sender district is not Selected !");
			System.out.println("SenderDsistrict Error");
		}
		if (courier.getReceiverDistrict().getId() == null) {
			bindingResult.rejectValue("receiverDistrict.id", null, "Reciver district is not Selected !");
			System.out.println("ReceiverDistrict Error");
		}
		if (!dontUseWeights) {
			int a = 0;
			for (CourierItem cItem : courier.getCourierItems()) {

				if (cItem.getLength() == null) {
					bindingResult.rejectValue("courierItems[" + a + "].length", null, "Required Field!");
				}
				if (cItem.getWidth() == null) {
					bindingResult.rejectValue("courierItems[" + a + "].width", null, "Required Field!");
				}
				if (cItem.getHeight() == null) {
					bindingResult.rejectValue("courierItems[" + a + "].height", null, "Required Field!");
				}
				if (cItem.getWeight() == null) {
					bindingResult.rejectValue("courierItems[" + a + "].weight", null, "Required Field!");
				}

				a++;
			}
		}

		if (bindingResult.hasErrors()) {
			
			if (courier.getSenderDistrict().getState().getCountry().getId()!=null) {
				model.addAttribute("stateList", stateService.getStateByCountryId(courier.getSenderDistrict().getState().getCountry().getId()));
			}
			
			if (courier.getSenderDistrict().getState().getId()!=null) {
				model.addAttribute("districtList", districtService.getDistrictByStateId(courier.getSenderDistrict().getState().getId()));
			}
			
			
			if (courier.getReceiverDistrict().getState().getCountry().getId()!=null) {
				model.addAttribute("stateList1", stateService.getStateByCountryId(courier.getSenderDistrict().getState().getCountry().getId()));
			}
			
			if (courier.getReceiverDistrict().getState().getId()!=null) {
				model.addAttribute("districtList", districtService.getDistrictByStateId(courier.getSenderDistrict().getState().getId()));
			}

			for (FieldError error : bindingResult.getFieldErrors()) {
				String fieldName = error.getField();
				String errorMessage = error.getDefaultMessage();
				System.out.println("Validation error for field '" + fieldName + "': " + errorMessage);
			}

			model.addAttribute("countryList", countryService.getAll());
			model.addAttribute("saveReceiver", saveReceiver);
			model.addAttribute("dontUseWeights", dontUseWeights);
			model.addAttribute("negativeMessage", "Please Fill Required Fields !");

			return "CourierForm";
		}

		if (dontUseWeights) {
			courier.setCourierItems(null);
		} else {

			for (CourierItem courierItem : courier.getCourierItems()) {
				courierItem.setCourier(courier);
			}
		}

		System.out.println("in Controller Save Receiver: " + saveReceiver);

		courierService.save(courier, Boolean.parseBoolean(saveReceiver));
		model.addAttribute("positiveMessage", "Courier Booked Successfully !");

		return "redirect:courier-form";
	}

	@GetMapping("/update-form")
	public String updateCourierForm(Model model) {

		model.addAttribute("countryList", countryService.getAll());

		String saveReceiver = "true";
		model.addAttribute("saveReceiver", saveReceiver);
		// model.addAttribute("dontUseWeights", false);

		Courier courier = courierService.getCourierById(552);
		model.addAttribute("courier", courier);

		return "CourierForm";
	}

	@GetMapping("/view-courier")
	public String courierManagement(Model model) {
		model.addAttribute("couriers", courierService.getAllCouriers());
		return "view_courier";
	}

	@GetMapping("/searchCourier")
	public String searchCourier(Model model,
			@Valid @RequestParam(value = "clientName", required = false) String clientName,
			@RequestParam(value = "bookingCode", required = false) String bookingCode,
			@RequestParam(value = "trackingCode", required = false) String trackingCode,
			@RequestParam(value = "bookingDate", required = false) LocalDate bookingDate,
			@RequestParam(value = "courierStatus", required = false) String courierStatus,
			@RequestParam(value = "startDate", required = false) LocalDate startDate,
			@RequestParam(value = "endDate", required = false) LocalDate endDate) {

		List<Courier> couriers = null;
		String field = "";

		if (clientName != null && !clientName.isBlank()) {
			couriers = courierService.getCourierByCustomerName(clientName);
			field = clientName;
		} else if (bookingCode != null && !bookingCode.isBlank()) {
			couriers = courierService.getCourierByBookingCode(bookingCode);
			field = bookingCode;
		} else if (trackingCode != null && !trackingCode.isBlank()) {
			couriers = courierService.getCourierByTrackingCode(trackingCode);
			field = trackingCode;
		} else if (bookingDate != null) {
			couriers = courierService.getCourierByDate(bookingDate);
			field = bookingDate.toString();
		} else if (courierStatus != null) {
			couriers = courierService.getCourierByCourierStatus(courierStatus);
			field = courierStatus;
		} else if (startDate != null && endDate != null) {
			couriers = courierService.getCourierByDateBetween(startDate, endDate);
			field = startDate.toString() + ", " + endDate.toString();
		} else {
			couriers = courierService.getAllCouriers();
		}

		if (!field.isBlank()) {
			if (couriers.isEmpty() || couriers == null) {
				model.addAttribute("positiveMessage", "No record found for ' " + field + " ' !");
			} else {
				// System.out.println(couriers);
				model.addAttribute("positiveMessage", couriers.size() + " record(s) matches '" + field + "'");
			}
		}

		// sending the required result
		model.addAttribute("couriers", couriers);

		return "view_courier";
	}

	@GetMapping("/seeParticular/{id}")
	public String getCourierById(@PathVariable Integer id, Model model) {

		model.addAttribute("courier", courierService.getCourierById(id));
		return "view_courier_details";
	}

	@GetMapping("/deleteParticular/{id}")
	public String deleteCourierById(@PathVariable Integer id, Model model) {
		courierService.deleteCourierById(id);
		return "redirect:/courier/view-courier";
	}

	/*
	 * @GetMapping("/view-courier-details") public String viewCourierManagement() {
	 * return "view_courier_details"; }
	 */

	@GetMapping("show-complete-courier")
	@ResponseBody
	public String showCompleteCourier() {
		Courier courier = courierService.getCourierById(253);
		courierService.normalSave(courier);
		courier = courierService.getCourierById(253);
		return courier.toString();
	}

	// ------------Registration Opening Page ----------------------------

	@GetMapping("create-account")
	public String abc(Model model) {

		Registation registation = new Registation();
		model.addAttribute("registation", registation);

		return "create-account";
	}

	// ------------Registration Save Method ----------------------------

	@PostMapping("create-account")
	public String Registration(@ModelAttribute Registation registation, BindingResult bindingResult, RedirectAttributes redirect,Model model) {

		if (bindingResult.hasErrors()) {
			return "create-account";
		} else {
			redirect.addFlashAttribute("success", "Successfully Register!");
			countryService.saveRegistrationForm(registation);
			return "redirect:login";
		}
	}

	// ------------Login Opening Page ----------------------------
 
	@GetMapping("login")
	public String showLogin(Model model) {
		model.addAttribute("registation",  new Registation());
		return "login";
	}

	// ------------Login Post Mapping Page ----------------------------

	@PostMapping("login")
	public String getLogin(Model model, @ModelAttribute Registation registation) {
		Registation user = courierService.getUser(registation);
		System.out.println("email : "+registation.getEmail());
		
		if (user == null) {
			model.addAttribute("warning", "warning");
			System.out.println("Hello :");
			return "login";
		}

		else {
			
			webRequest.setAttribute("user", user, RequestAttributes.SCOPE_SESSION);
			Registation sessionUser= (Registation) webRequest.getAttribute("user", RequestAttributes.SCOPE_SESSION);
			System.out.println("email : "+sessionUser.getName());
			return "redirect:/Dashboard";
		}

	}
	
	@GetMapping("/logout")
	public String getLogout(Model model,Registation user,RedirectAttributes redirect)
	{
		webRequest.removeAttribute("user", RequestAttributes.SCOPE_SESSION);
		redirect.addFlashAttribute("logout", "You Logged-Out Successfully !");
		return "redirect:/courier/login";
	}
}
