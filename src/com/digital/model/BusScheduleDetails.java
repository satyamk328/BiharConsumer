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
	
	private List<BusAmenity> amenities = new ArrayList<>();
	private List<BusCityStopLocationsDetails> boardingLocations = new ArrayList<>();
	private List<BusCityStopLocationsDetails> droppingLocations = new ArrayList<>();
	private List<BusCancellationPolicies> cancellationPolicy = new ArrayList<>();
	
	private BusDetails busDetails;
	private List<Double> fares;
	
	private Boolean idProofRequired = false;
	private Boolean isAc = false;
	private Boolean isSeater;
	private Boolean isSleeper;
		
	private boolean isRefundable= false;
	private double totalSeats;
	private double availableSeats;
	
	private Long scheduleId;
	private Long busId;
	private Double sleeperFare;
	private Double seaterFare;
	private String srcCityName; 
	private String destCityName;
	private String destinationId;
	private String sourceId;
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
	private String busType;
	private String classType;
	private String seatType;
	private double duration;
	private int travelDurationDays = 1;
	
}
