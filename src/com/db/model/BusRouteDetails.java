package com.db.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Satyam Kumar
 *
 */
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
	/**
	 * @return the boardingLocations
	 */
	public List<BusBoadingStopingDetails> getBoardingLocations() {
		return boardingLocations;
	}
	/**
	 * @param boardingLocations the boardingLocations to set
	 */
	public void setBoardingLocations(List<BusBoadingStopingDetails> boardingLocations) {
		this.boardingLocations = boardingLocations;
	}
	/**
	 * @return the droppingLocations
	 */
	public List<BusBoadingStopingDetails> getDroppingLocations() {
		return droppingLocations;
	}
	/**
	 * @param droppingLocations the droppingLocations to set
	 */
	public void setDroppingLocations(List<BusBoadingStopingDetails> droppingLocations) {
		this.droppingLocations = droppingLocations;
	}
	/**
	 * @return the busInfo
	 */
	public List<BusDetails> getBusInfo() {
		return busInfo;
	}
	/**
	 * @param busInfo the busInfo to set
	 */
	public void setBusInfo(List<BusDetails> busInfo) {
		this.busInfo = busInfo;
	}
	/**
	 * @return the cancellationPolicy
	 */
	public List<BusCancellationPolicies> getCancellationPolicy() {
		return cancellationPolicy;
	}
	/**
	 * @param cancellationPolicy the cancellationPolicy to set
	 */
	public void setCancellationPolicy(List<BusCancellationPolicies> cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}
	/**
	 * @return the amenities
	 */
	public List<Integer> getAmenities() {
		return amenities;
	}
	/**
	 * @param amenities the amenities to set
	 */
	public void setAmenities(List<Integer> amenities) {
		this.amenities = amenities;
	}
	/**
	 * @return the bustypes
	 */
	public List<BusType> getBustypes() {
		return bustypes;
	}
	/**
	 * @param bustypes the bustypes to set
	 */
	public void setBustypes(List<BusType> bustypes) {
		this.bustypes = bustypes;
	}
	/**
	 * @return the fares
	 */
	public List<Double> getFares() {
		return fares;
	}
	/**
	 * @param fares the fares to set
	 */
	public void setFares(List<Double> fares) {
		this.fares = fares;
	}
	/**
	 * @return the rating
	 */
	public List<BusRating> getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(List<BusRating> rating) {
		this.rating = rating;
	}
	/**
	 * @return the inclTaxes
	 */
	public boolean isInclTaxes() {
		return inclTaxes;
	}
	/**
	 * @param inclTaxes the inclTaxes to set
	 */
	public void setInclTaxes(boolean inclTaxes) {
		this.inclTaxes = inclTaxes;
	}
	/**
	 * @return the classType
	 */
	public String getClassType() {
		return classType;
	}
	/**
	 * @param classType the classType to set
	 */
	public void setClassType(String classType) {
		this.classType = classType;
	}
	/**
	 * @return the operatorId
	 */
	public String getOperatorId() {
		return operatorId;
	}
	/**
	 * @param operatorId the operatorId to set
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	/**
	 * @return the providerId
	 */
	public String getProviderId() {
		return providerId;
	}
	/**
	 * @param providerId the providerId to set
	 */
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	/**
	 * @return the travelsName
	 */
	public String getTravelsName() {
		return travelsName;
	}
	/**
	 * @param travelsName the travelsName to set
	 */
	public void setTravelsName(String travelsName) {
		this.travelsName = travelsName;
	}
	/**
	 * @return the busType
	 */
	public String getBusType() {
		return busType;
	}
	/**
	 * @param busType the busType to set
	 */
	public void setBusType(String busType) {
		this.busType = busType;
	}
	/**
	 * @return the routeId
	 */
	public String getRouteId() {
		return routeId;
	}
	/**
	 * @param routeId the routeId to set
	 */
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	/**
	 * @return the duration
	 */
	public double getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}
	/**
	 * @return the travelDurationDays
	 */
	public int getTravelDurationDays() {
		return travelDurationDays;
	}
	/**
	 * @param travelDurationDays the travelDurationDays to set
	 */
	public void setTravelDurationDays(int travelDurationDays) {
		this.travelDurationDays = travelDurationDays;
	}
	/**
	 * @return the idProofRequired
	 */
	public boolean isIdProofRequired() {
		return idProofRequired;
	}
	/**
	 * @param idProofRequired the idProofRequired to set
	 */
	public void setIdProofRequired(boolean idProofRequired) {
		this.idProofRequired = idProofRequired;
	}
	/**
	 * @return the isAc
	 */
	public boolean isAc() {
		return isAc;
	}
	/**
	 * @param isAc the isAc to set
	 */
	public void setAc(boolean isAc) {
		this.isAc = isAc;
	}
	/**
	 * @return the isSleeper
	 */
	public boolean isSleeper() {
		return isSleeper;
	}
	/**
	 * @param isSleeper the isSleeper to set
	 */
	public void setSleeper(boolean isSleeper) {
		this.isSleeper = isSleeper;
	}
	/**
	 * @return the layoutType
	 */
	public String getLayoutType() {
		return layoutType;
	}
	/**
	 * @param layoutType the layoutType to set
	 */
	public void setLayoutType(String layoutType) {
		this.layoutType = layoutType;
	}
	/**
	 * @return the isSeater
	 */
	public boolean isSeater() {
		return isSeater;
	}
	/**
	 * @param isSeater the isSeater to set
	 */
	public void setSeater(boolean isSeater) {
		this.isSeater = isSeater;
	}
	/**
	 * @return the isRefundable
	 */
	public boolean isRefundable() {
		return isRefundable;
	}
	/**
	 * @param isRefundable the isRefundable to set
	 */
	public void setRefundable(boolean isRefundable) {
		this.isRefundable = isRefundable;
	}
	/**
	 * @return the totalSeats
	 */
	public double getTotalSeats() {
		return totalSeats;
	}
	/**
	 * @param totalSeats the totalSeats to set
	 */
	public void setTotalSeats(double totalSeats) {
		this.totalSeats = totalSeats;
	}
	/**
	 * @return the availableSeats
	 */
	public double getAvailableSeats() {
		return availableSeats;
	}
	/**
	 * @param availableSeats the availableSeats to set
	 */
	public void setAvailableSeats(double availableSeats) {
		this.availableSeats = availableSeats;
	}
	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}
	/**
	 * @return the arrivalDate
	 */
	public String getArrivalDate() {
		return arrivalDate;
	}
	/**
	 * @param arrivalDate the arrivalDate to set
	 */
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	/**
	 * @return the arrivalTime
	 */
	public String getArrivalTime() {
		return arrivalTime;
	}
	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	/**
	 * @return the departureDate
	 */
	public String getDepartureDate() {
		return departureDate;
	}
	/**
	 * @param departureDate the departureDate to set
	 */
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	/**
	 * @return the departureTime
	 */
	public String getDepartureTime() {
		return departureTime;
	}
	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	/**
	 * @return the seatType
	 */
	public String getSeatType() {
		return seatType;
	}
	/**
	 * @param seatType the seatType to set
	 */
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTripid() {
		return tripid;
	}
	public void setTripid(String tripid) {
		this.tripid = tripid;
	}
}
