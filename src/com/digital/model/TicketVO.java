package com.digital.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketVO {
	private Long ticketId;
	private Long custId;
	private Long operatorId;
	private Long busId;
	private Long routeId;
	private String dbTripId;
	private String pnr;
	private String tripId;
	private String travalName;
	private String busType;
	private Boolean isActive;
	private Boolean isSeepter;
	private Boolean isSeater;
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
	private String custName;
	private int age;
	private String email;
	private String gender;
	private int phoneNumber;
	private String idType;
	private String idNumber;
	private Boolean isLicence;

}
