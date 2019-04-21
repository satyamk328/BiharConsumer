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
public class BusScheduleDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<BusCityStopLocationsDetails> boardingLocations = new ArrayList<>();
	private List<BusCityStopLocationsDetails> droppingLocations = new ArrayList<>();
	private List<BusDetails> busInfo = new ArrayList<>();
	private List<BusCancellationPolicies> cancellationPolicy = new ArrayList<>();
	private List<BusAmenity> amenities = new ArrayList<>();
	private List<BusType> bustypes = new ArrayList<>();
	private List<Double> fares = new ArrayList<>();
	private List<BusRating> rating = new ArrayList<>();
	private boolean inclTaxes = true;
	private String classType;
	
	private Long operatorId;  
	private String providerId;  
	private String travelsName;
	private String busType;
	private double duration;
	private int travelDurationDays = 1;
	private boolean idProofRequired = false;
	private boolean isAc = false;
	private boolean isSleeper = false;
	private String layoutType;
	private boolean isSeater = true;
	private boolean isRefundable= false;
	private double totalSeats;
	private double availableSeats;
	private double distance; 
	private String seatType; 
	
	private Long scheduleId;
	private Long busId;
	private Double sleeperFare;
	private Double seaterFare;
	private String srcCity; 
	private String destCity;
	private String srcStops; 
	private String destStops;
	private String departureDate;
	private String departureTime;
	private String arrivalDate;
	private String arrivalTime;
	private int isFixedFare;
}
