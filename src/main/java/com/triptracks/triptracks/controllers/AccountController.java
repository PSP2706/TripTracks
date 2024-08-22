package com.triptracks.triptracks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.triptracks.triptracks.dto.RegisterDto;
import com.triptracks.triptracks.models.User;
import com.triptracks.triptracks.repositories.UserRepository;
import com.triptracks.triptracks.services.UserService;

import jakarta.validation.Valid;

@Controller
public class AccountController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		RegisterDto regDto=new RegisterDto();
		model.addAttribute("registerDto",regDto);
		model.addAttribute("success",false);
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(Model model,@Valid @ModelAttribute RegisterDto regDto,BindingResult result) {
		if(!regDto.getPassword().equals(regDto.getConfirmPassword())) {
			result.addError(
	                new FieldError("registerDto", "confirmPassword",
	                    "Password and confirm password do not match")
	            );
		}
		User user=userRepo.findByEmail(regDto.getEmail());
		if(user!=null) {
			 result.addError(
		                new FieldError("registerDto", "email",
		                    "Email address is already used")
		            );
		}
		if (result.hasErrors()) {
            return "register";
        }
		try {
			if(regDto.getRole().equals("ADMIN")) {
				userService.saveUserAdmin(regDto);
			}
			else {
				userService.saveUserCustomer(regDto);
			}
			model.addAttribute("success",true);
			model.addAttribute("registerDto", new RegisterDto());
		}
		catch (Exception e) {
            result.addError(
                new FieldError("registerDto", "name", e.getMessage())
            );
        }
		return "register";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
}
