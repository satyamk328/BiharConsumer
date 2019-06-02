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
	
	private List<Amenity> amenities = new ArrayList<>();
	private List<CityStop> boardingLocations = new ArrayList<>();
	private List<CityStop> droppingLocations = new ArrayList<>();
	private List<CancelPolicies> cancellationPolicy = new ArrayList<>();
	
	private BusDetails busDetails = new BusDetails();
	private List<Double> fares;
	private int totalSeats;
	private int availableSeats;
	private Boolean idProofRequired = false;
	
		
	private boolean isRefundable= false;
	
	private Long scheduleId;
	private Long busId;
	private Double sleeperFare;
	private Double seaterFare;
	private String srcCityName; 
	private String destCityName;
	private Long destinationId;
	private Long sourceId;
	private Integer srcCitySequance;
	private Integer destCitySequance;
	private String srcStops; 
	private String destStops;
	private String departureDate;
	private String departureTime;
	private String arrivalDate;
	private String arrivalTime;
	private int isFixedFare;
	
	private Double rating = 2.5;
	private String classType;
	
	private double distance = 0;
	private double duration;
	private int travelDurationDays = 1;
	
}
