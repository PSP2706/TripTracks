package com.triptracks.triptracks.services;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.triptracks.triptracks.dto.RegisterDto;
import com.triptracks.triptracks.models.User;
import com.triptracks.triptracks.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=userRepo.findByEmail(email);
		if(user!=null) {
			return org.springframework.security.core.userdetails.User
					.withUsername(user.getEmail())
					.password(user.getPassword())
					.roles(user.getRole())
					.build();
		}
		throw new UsernameNotFoundException("User does not exist");
	}

	public User saveUser(RegisterDto regDto,String role) {
		User user=new User();
		user.setName(regDto.getName());
		user.setEmail(regDto.getEmail());
		user.setPassword(pwdEncoder.encode(regDto.getPassword()));
		user.setRole(role);
		return userRepo.save(user);
	}
	public User saveUserAdmin(RegisterDto regDto) {
		return saveUser(regDto,"ADMIN");
	}
	
	public User saveUserCustomer(RegisterDto regDto) {
		return saveUser(regDto,"CUSTOMER");
	}
	
}
