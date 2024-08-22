package com.triptracks.triptracks.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import com.triptracks.triptracks.dto.AddTrainDto;
import com.triptracks.triptracks.dto.BookTrainDto;
import com.triptracks.triptracks.dto.TrainSearchDto;
import com.triptracks.triptracks.dto.UpdateTrainDto;
import com.triptracks.triptracks.models.Booking;
import com.triptracks.triptracks.models.Train;
import com.triptracks.triptracks.models.User;
import com.triptracks.triptracks.repositories.BookingRepository;
import com.triptracks.triptracks.repositories.TrainRepository;
import com.triptracks.triptracks.repositories.UserRepository;

@Service
public class TrainService {
	
	@Autowired
	private TrainRepository trainRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BookingRepository bookingRepo;
	
	//ADMIN FUNCTIONALITY
	public Train saveTrain(AddTrainDto addTrainDto,Authentication authentication) {
		UserDetails userDetails=(UserDetails)authentication.getPrincipal();
		User user=userRepo.findByEmail(userDetails.getUsername());
		Train train=new Train();
		train.setName(addTrainDto.getName());
		train.setSource(addTrainDto.getSource());
		train.setDestination(addTrainDto.getDestination());
		train.setAvailableSeats(addTrainDto.getAvailableSeats());
		train.setAddedBy(user);
		return trainRepo.save(train);
	}
	//CUSTOMER FUNCTIONALITY
	public List<Train> searchTrains(TrainSearchDto trainSearchDto){
		String src=trainSearchDto.getSource();
		String dest=trainSearchDto.getDestination();
		List<Train> trains=trainRepo.findBySourceAndDestination(src,dest);
		return trains;
	}
	//CUSTOMER FUNCTIONALITY
	public List<Train> viewAllTrains(){
		List<Train> trains=trainRepo.findAll();
		return trains;
	}
	//ADMIN FUNCTIONALITY
	public List<Train> getTrainsAddedByUser(Authentication authentication){
		UserDetails userDetails=(UserDetails)authentication.getPrincipal();
		User user=userRepo.findByEmail(userDetails.getUsername());
		List<Train> trains=trainRepo.findByAddedBy(user);
		return trains;
	}
	//CUSTOMER FUNCTIONALITY
	public String bookTrain(BookTrainDto bookTrainDto,Authentication authentication) {
		Train train=trainRepo.findByName(bookTrainDto.getName());
		int seatsRequired=bookTrainDto.getSeatsRequired();
		if(train.getAvailableSeats()>=seatsRequired) {
			UserDetails userDetails=(UserDetails)authentication.getPrincipal();
			User user=userRepo.findByEmail(userDetails.getUsername());
			train.setAvailableSeats(train.getAvailableSeats()-seatsRequired);
			trainRepo.save(train);
			
			Booking booking=new Booking();
			booking.setTrain(train);
			booking.setUser(user);
			booking.setLocalDate(LocalDate.now());
			booking.setSeatsBooked(seatsRequired);
			bookingRepo.save(booking);
			return "success";
		}
		return "failure";
	}
}



//System.out.println("BookTrainDto: " + bookTrainDto.getName() + ", " + bookTrainDto.getSeatsRequired());
//
//		Train train=trainRepo.findByName(bookTrainDto.getName());
//		int seatsRequired=bookTrainDto.getSeatsRequired();
//		
//		if(train.getAvailableSeats()>=seatsRequired) {
//			UserDetails userDetails=(UserDetails)authentication.getPrincipal();
//			User user=userRepo.findByEmail(userDetails.getUsername());
//			train.setAvailableSeats(train.getAvailableSeats()-seatsRequired);
//			trainRepo.save(train);
//			user.getTrainsBooked().add(train);
//			userRepo.save(user);
//		return "success";
//	}
//	return "Seats not available";