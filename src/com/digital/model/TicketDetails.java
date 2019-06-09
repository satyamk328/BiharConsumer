package com.digital.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long ticketId;
	private Long scheduleId;
	private String userId;
	private Long busId;
	private String pnr;
	private Long seatId;
	private String tripId;
	private String srcCityName;
	private String destCityName;
	private String travelName;
	private String busType;
	private Double distance;
	private Boolean isAc = false;
	private String boadingPoint;
	private String droppingPoint;
	private String departureDate;
	private String departureTime;
	private String arrivalDate;
	private String arrivalTime;
	private String seatType;
	private String seatNumber;
	private String seatName;
	private Boolean isLowerBerth = false;
	private Double totalFare;
	private String chartStatus;
	private String customerName;
	private Long age;
	private String email;
	private String gender;
	private String phoneNumber;
	private Boolean isLicence;
	private Date bookingDate;
	private String bookBy;
}
