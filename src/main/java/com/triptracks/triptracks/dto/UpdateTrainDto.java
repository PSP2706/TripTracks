package com.triptracks.triptracks.dto;

public class UpdateTrainDto {

	private String source;
	private String destination;
	private String extraSeats;
	public UpdateTrainDto() {}
	public UpdateTrainDto(String source, String destination, String extraSeats) {
		super();
		this.source = source;
		this.destination = destination;
		this.extraSeats = extraSeats;
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
	public String getExtraSeats() {
		return extraSeats;
	}
	public void setExtraSeats(String extraSeats) {
		this.extraSeats = extraSeats;
	}
	
	
}
