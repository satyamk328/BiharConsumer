package com.digital.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long ticketId;
	private Long scheduleId;
	private Long userId;
	private Long busId;
	private String pnr;
	private Long seatId;
	private String tripId;
	private String travelName;
	private String busType;
	private Boolean isAc = false;
	private String boadingPoint;
	private String droppingPoint;
	private String departureDate;
	private String departureTime;
	private String arrivalDate;
	private String arrivalTime;
	private String seatNumber;
	private String seatName;
	private String isLowerBerth;
	private double totalFare;
	private String chartStatus;
	private String customerName;
	private int age;
	private String email;
	private String gender;
	private int phoneNumber;

}
