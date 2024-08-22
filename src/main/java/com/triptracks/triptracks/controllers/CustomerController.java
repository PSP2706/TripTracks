package com.triptracks.triptracks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triptracks.triptracks.dto.BookTrainDto;
import com.triptracks.triptracks.dto.TrainSearchDto;
import com.triptracks.triptracks.models.Booking;
import com.triptracks.triptracks.models.Train;
import com.triptracks.triptracks.models.User;
import com.triptracks.triptracks.repositories.BookingRepository;
import com.triptracks.triptracks.repositories.UserRepository;
import com.triptracks.triptracks.services.TrainService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private TrainService trainService;
	
	
	@Autowired
	private BookingRepository bookingRepo;
	
	
	@Autowired
	private UserRepository userRepo;
	
	
	@GetMapping("/home")
	 public String showHomePage() {
		 return "customer/home";
	 }
	
	
	@GetMapping("/view-all-trains")
	public String getAllTrains(Model model) {
		List<Train> trains=trainService.viewAllTrains();

		model.addAttribute("noTrainsFound",true);
	     if (trains.isEmpty()) {
	         model.addAttribute("trainsFound", false);
	     } else {
	    	 model.addAttribute("noTrainsFound",false);
	         model.addAttribute("trainsFound", true);
	         model.addAttribute("bookTrainDto", new BookTrainDto());
	         model.addAttribute("trains", trains);
	     }
		return "customer/view-all-trains";
	}
	
	
	@GetMapping("/search-trains")
	public String searchTrainsPage(Model model) {
		model.addAttribute("trainSearchDto",new TrainSearchDto());
		return "customer/search-trains";
	}
	
	
	@PostMapping("/search-trains")
	public String searchTrainsResults(Model model,TrainSearchDto trainSearchDto) {
		List<Train> trains=trainService.searchTrains(trainSearchDto);
		model.addAttribute("noTrainsFound",true);
		model.addAttribute("trainSearchDto",new TrainSearchDto());
		if (trains.isEmpty()) {
	         model.addAttribute("trainsFound", false);
	     } else {
	    	 model.addAttribute("noTrainsFound",false);
	         model.addAttribute("trainsFound", true);
	         model.addAttribute("trains", trains);
	         model.addAttribute("bookTrainDto", new BookTrainDto());
	     }
		return "customer/search-trains";
	}
	
	
	
	@PostMapping("/book-train")
	public String bookTrainTicket(
	        @ModelAttribute BookTrainDto bookTrainDto,
	        BindingResult result,
	        RedirectAttributes redirectAttributes,
	        Model model,
	        Authentication authentication) {

		List<Train> trains=trainService.viewAllTrains();

		model.addAttribute("noTrainsFound",true);
	     if (trains.isEmpty()) {
	         model.addAttribute("trainsFound", false);
	     } else {
	    	 model.addAttribute("noTrainsFound",false);
	         model.addAttribute("trainsFound", true);
	         model.addAttribute("bookTrainDto", new BookTrainDto());
	         model.addAttribute("trains", trains);
	     }
		
	    if (result.hasErrors()) {
	        model.addAttribute("failure", true);
	        return "customer/view-all-trains";
	    }

	    String response = trainService.bookTrain(bookTrainDto, authentication);

	    if ("success".equals(response)) {
	        model.addAttribute("success", true);
	        model.addAttribute("bookTrainDto", new BookTrainDto());
	        return "customer/view-all-trains";
	    } else {
	        model.addAttribute("failure", true);
	        model.addAttribute("bookTrainDto", new BookTrainDto());
	        return "customer/view-all-trains";
	    }
	}
	
	
	@PostMapping("/book-searched-train")
	public String bookSearchedTrain(@ModelAttribute BookTrainDto bookTrainDto,
	        BindingResult result,
	        Model model,
	        Authentication authentication) {

	    model.addAttribute("trainSearchDto", new TrainSearchDto());
	 
	    

	    if (result.hasErrors()) {
	        model.addAttribute("failureBooking", true);
	        model.addAttribute("successBooking", false);
	        return "customer/search-trains";
	    }

	    String response = trainService.bookTrain(bookTrainDto, authentication);

	    model.addAttribute("successBooking", "success".equals(response));
	    model.addAttribute("failureBooking", !"success".equals(response));
	    model.addAttribute("bookTrainDto", new BookTrainDto());

	    return "customer/search-trains";
	}
	
	

	@GetMapping("/dashboard")
	public String getCustomerDashboard(Model model,Authentication authentication) {
		UserDetails userDetails=(UserDetails)authentication.getPrincipal();
		User user=userRepo.findByEmail(userDetails.getUsername());
		List<Booking> bookings=bookingRepo.findByUser(user);
		model.addAttribute("user", user);
		model.addAttribute("noBookingsFound",false);
		model.addAttribute("bookingsFound", false);
		if(bookings.isEmpty()) {
			model.addAttribute("noBookingsFound", true);
		}
		else {
			model.addAttribute("bookingsFound", true);
			model.addAttribute("bookings", bookings);
		}
		return "customer/dashboard";
	}
	
	
}

//if (response.equals("success")) {
//redirectAttributes.addFlashAttribute("success", true);
//return "redirect:/customer/dashboard";
//} else {
//model.addAttribute("failure", true);
//return "customer/book-train";
//}


//if(trains.isEmpty()) {
//model.addAttribute("noTrainsFound",true);
//}
//else {
//model.addAttribute("noTrainsFound",false);
//model.addAttribute("trains", trains);
//}