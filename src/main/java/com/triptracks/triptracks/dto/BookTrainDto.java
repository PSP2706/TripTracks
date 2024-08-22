package com.triptracks.triptracks.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BookTrainDto {

	@NotEmpty
	private String name;
	@NotEmpty
	private String source;
	@NotEmpty
	private String destination;
	@NotNull
    @Min(1)
	private int seatsRequired;
	
	public BookTrainDto() {}

	public BookTrainDto(@NotEmpty String name, @NotEmpty String source, @NotEmpty String destination,
			@NotEmpty int seatsRequired) {
		super();
		this.name = name;
		this.source = source;
		this.destination = destination;
		this.seatsRequired = seatsRequired;
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

	public int getSeatsRequired() {
		return seatsRequired;
	}

	public void setSeatsRequired(int seatsRequired) {
		this.seatsRequired = seatsRequired;
	}
	
	
}
