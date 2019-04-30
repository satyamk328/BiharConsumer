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
	
	private boolean idProofRequired = false;
	private boolean isAc = false;
		
	private boolean isRefundable= false;
	private double totalSeats;
	private double availableSeats;
	
	
	private Long scheduleId;
	private Long busId;
	private Double sleeperFare;
	private Double seaterFare;
	private String srcCity; 
	private String destCity;
	private Integer srcCitySequance;
	private Integer destCitySequance;
	private String srcStops; 
	private String destStops;
	private String departureDate;
	private String departureTime;
	private String arrivalDate;
	private String arrivalTime;
	private int isFixedFare;
	
	private BusDetails busDetails;
	private List<BusCityStopLocationsDetails> boardingLocations = new ArrayList<>();
	private List<BusCityStopLocationsDetails> droppingLocations = new ArrayList<>();
	private List<BusCancellationPolicies> cancellationPolicy = new ArrayList<>();
	private List<BusAmenity> amenities = new ArrayList<>();
	
	private BusRating busRating;
	private BusType busType;
	private String classType;
	private double duration;
	private int travelDurationDays = 1;
	
}
