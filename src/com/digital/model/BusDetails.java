package com.digital.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Satyam Kumar
 *
 */
@Setter
@Getter
public class BusDetails implements Serializable{

	private static final long serialVersionUID = -6508045063085127065L;
	private Long busId;
	private Long ownerId;
	private Long layoutId;
	private String busNumber;
	private String travelsName;
	private String registationNumber;
	private String color;
	private Long totalSeats;
	
	private Boolean isAc = false;
	private Boolean isSeater;
	private Boolean isSleeper;
	private String busType;
	private String seatType;
	
	private String layout;
	private String layOutDescription;
	
	private List<SeatDetails> seatDetails = new ArrayList<SeatDetails>();
	private List<SeatDetails> lowerSeatDetails = new ArrayList<SeatDetails>();
	private List<SeatDetails> upperSeatDetails = new ArrayList<SeatDetails>();
}
