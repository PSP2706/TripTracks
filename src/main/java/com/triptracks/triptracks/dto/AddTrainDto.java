package com.triptracks.triptracks.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AddTrainDto {

	@NotEmpty(message = "Train name cannot be empty")
	private String name;

	@NotEmpty(message = "Source cannot be empty")
	private String source;

	@NotEmpty(message = "Destination cannot be empty")
	private String destination;

	@NotNull(message = "Available seats cannot be null")
	@Min(value = 1, message = "Available seats must be at least 1")
	private Integer availableSeats;

	public AddTrainDto() {}

	public AddTrainDto(String name, String source, String destination, Integer availableSeats) {
		super();
		this.name = name;
		this.source = source;
		this.destination = destination;
		this.availableSeats = availableSeats;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
}
