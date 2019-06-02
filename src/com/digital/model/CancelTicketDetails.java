package com.digital.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CancelTicketDetails {
	
	private Long cancelId;
	private Long ticketId; 
	private Long scheduleId; 
	private String userId; 
	private Long busId; 
	private String pnr; 
	private Long seatId; 
	private String tripId; 
	private String travelName; 
	private String busType; 
	private Boolean isAc; 
	private String boadingPoint; 
	private String droppingPoint; 	
	private String departureDate; 
	private String departureTime; 
	private String arrivalDate; 
	private String arrivalTime; 
	private String seatType; 
	private String seatNumber; 
	private String seatName; 
	private Boolean isLowerBerth; 
	private Double totalFare; 
	private String chartStatus; 
	private String customerName; 
	private Integer age; 
	private String email; 
	private String gender; 
	private Long phoneNumber; 
	private Boolean isLicence; 
	private Date bookingDate;
	private Date cancelDate;
	private Long policyId;
	private Double refundAmount;
}
