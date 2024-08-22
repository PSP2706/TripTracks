package com.triptracks.triptracks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.triptracks.triptracks.models.*;

public interface BookingRepository extends JpaRepository<Booking,Long>{
	List<Booking> findByTrain(Train train);
	List<Booking> findByUser(User user);
	List<Booking> findByTrainIn(List<Train> trains);
}
