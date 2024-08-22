package com.triptracks.triptracks.dto;

import jakarta.validation.constraints.NotEmpty;

public class TrainSearchDto {
 
	@NotEmpty
	private String source;
	@NotEmpty
	private String destination;
	
	public TrainSearchDto() {}

	public TrainSearchDto(@NotEmpty String source, @NotEmpty String destination) {
		super();
		this.source = source;
		this.destination = destination;
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

	
}
