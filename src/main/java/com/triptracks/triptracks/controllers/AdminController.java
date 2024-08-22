package com.triptracks.triptracks.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.triptracks.triptracks.dto.AddTrainDto;
import com.triptracks.triptracks.models.Booking;
import com.triptracks.triptracks.models.Train;
import com.triptracks.triptracks.models.User;
import com.triptracks.triptracks.repositories.BookingRepository;
import com.triptracks.triptracks.repositories.TrainRepository;
import com.triptracks.triptracks.repositories.UserRepository;
import com.triptracks.triptracks.services.TrainService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private TrainService trainService;
	@Autowired
	private TrainRepository trainRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BookingRepository bookingRepo;

	 @GetMapping("/home")
	 public String showHomePage() {
		 return "admin/home";
	 }
	 @GetMapping("/add-train")
	 public String getAddNewTrainPage(Model model) {
		 AddTrainDto addTrainDto=new AddTrainDto();
		 model.addAttribute("addTrainDto", addTrainDto);
		 model.addAttribute("success",false);
		 return "admin/add-train";
	 }
	 
	 @PostMapping("/add-train")
	 public String addNewTrain(@Valid @ModelAttribute AddTrainDto addTrainDto, Model model,BindingResult result,Authentication authentication) {
		Train train=trainRepo.findByName(addTrainDto.getName());
		if(train!=null) {
			result.addError(
					new FieldError("addTrainDto","name","Train with this name is already present")
					);
		}
		if(result.hasErrors()) {
			model.addAttribute("error", true);
			return "admin/add-train";
		}
		try {
			Train newTrain=trainService.saveTrain(addTrainDto, authentication);
			model.addAttribute("success", true);
			model.addAttribute("addTrainDto", new AddTrainDto());
		}
		catch(Exception e) {
			result.addError(
					new FieldError("addTrainDto", "error", e.getMessage())

	            );
		}
		return "admin/add-train";
	 }
	 @GetMapping("/view-trains")
	 public String findAllTrainsAddedByUser(Model model, Authentication authentication) {
	     List<Train> trains = trainService.getTrainsAddedByUser(authentication);
	     model.addAttribute("noTrainsFound",true);
	     if (trains.isEmpty()) {
	         model.addAttribute("trainsFound", false);
	     } else {
	    	 model.addAttribute("noTrainsFound",false);
	         model.addAttribute("trainsFound", true);
	         model.addAttribute("trains", trains);
	     }
	     return "admin/view-trains";
	 }
	 
	 @GetMapping("/dashboard")
	 public String getAdminDashboard(Model model, Authentication authentication) {
	     UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	     User user = userRepo.findByEmail(userDetails.getUsername());
	     
	     if (user == null) {
	         model.addAttribute("noBookingsYet", true);
	         model.addAttribute("bookingsFound", false);
	         return "admin/dashboard";
	     }
	     
	     List<Train> trains = trainRepo.findByAddedBy(user);
	     
	     if (trains == null || trains.isEmpty()) {
	         model.addAttribute("noBookingsYet", true);
	         model.addAttribute("bookingsFound", false);
	         return "admin/dashboard";
	     }
	     
	     List<Booking> adminBookings = bookingRepo.findByTrainIn(trains);
	     
	     if (adminBookings.isEmpty()) {
	         model.addAttribute("noBookingsYet", true);
	         model.addAttribute("bookingsFound", false);
	     } else {
	         model.addAttribute("noBookingsYet", false);
	         model.addAttribute("bookingsFound", true);
	         model.addAttribute("adminBookings", adminBookings);
	     }
	     
	     return "admin/dashboard";
	 }


	 
	 @GetMapping("/admin/view-trains")
	 public String getAlternateAdmin() {
		 return "admin/view-trains";
	 }
}
