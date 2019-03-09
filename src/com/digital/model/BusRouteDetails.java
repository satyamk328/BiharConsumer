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
public class BusRouteDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<BusBoadingStopingDetails> boardingLocations = new ArrayList<>();
	private List<BusBoadingStopingDetails> droppingLocations = new ArrayList<>();
	private List<BusDetails> busInfo = new ArrayList<>();
	private List<BusCancellationPolicies> cancellationPolicy = new ArrayList<>();
	private List<Integer> amenities = new ArrayList<>();
	private List<BusType> bustypes = new ArrayList<>();
	private List<Double> fares = new ArrayList<>();
	private List<BusRating> rating = new ArrayList<>();
	private boolean inclTaxes = true;
	private String classType;
	private String operatorId;  
	private String providerId;  
	private String travelsName;
	private String busType;
	private String routeId;
	private double duration;
	private int travelDurationDays;
	private boolean idProofRequired = false;
	private boolean isAc = false;
	private boolean isSleeper = false;
	private String layoutType;
	private boolean isSeater = true;
	private boolean isRefundable= false;
	private double totalSeats;
	private double availableSeats;
	private double distance; 
	private String arrivalDate;
	private String arrivalTime;
	private String departureDate;
	private String departureTime;
	private String seatType; 
	private String source; 
	private String destination;
	private String tripid;
	
}
