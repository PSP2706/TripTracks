package com.triptracks.triptracks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;
import com.triptracks.triptracks.models.User;
import com.triptracks.triptracks.repositories.UserRepository;

@Controller
public class ContentController {
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/")
	public String homePage(Authentication authentication, Model model) {
	    if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
	        return "index";
	    }
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    User user = userRepo.findByEmail(userDetails.getUsername());
	    if (user != null) {
	        if (user.getRole().equals("ADMIN")) {
	            // Convert the user's name to uppercase
	            String upperCaseName = user.getName().toUpperCase();
	            model.addAttribute("userName", upperCaseName);
	            return "admin/home";
	        } else {
	        	String upperCaseName = user.getName().toUpperCase();
	            model.addAttribute("userName", upperCaseName);
	            return "customer/home";
	        }
	    }
	    return "index";
	}

	@GetMapping("/403")
	public String get403() {
		//If access is denied of a page, go to 403.html
		return "403";
	}
	
	@GetMapping("/404")
	public String get404() {
		//If page is not found, go to 404.html
		return "404";
	}
}
