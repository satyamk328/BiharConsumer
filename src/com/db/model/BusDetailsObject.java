package com.db.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Satyam Kumar
 *
 */
public class BusDetailsObject implements Serializable{
	
	private static final long serialVersionUID = 1068912061763283486L;
	private List<BusRouteDetails> availableRoutes = new ArrayList<>();
	private List<String> departureTimeList = new ArrayList<>();
	private List<String> arrivalTimeList = new ArrayList<>();
	private List<BusAmenity> amenitiesList = new ArrayList<>();
	private List<String> busOperators = new ArrayList<>();
	/**
	 * @return the availableRoutes
	 */
	public List<BusRouteDetails> getAvailableRoutes() {
		return availableRoutes;
	}
	/**
	 * @param availableRoutes the availableRoutes to set
	 */
	public void setAvailableRoutes(List<BusRouteDetails> availableRoutes) {
		this.availableRoutes = availableRoutes;
	}
	/**
	 * @return the departureTimeList
	 */
	public List<String> getDepartureTimeList() {
		return departureTimeList;
	}
	/**
	 * @param departureTimeList the departureTimeList to set
	 */
	public void setDepartureTimeList(List<String> departureTimeList) {
		this.departureTimeList = departureTimeList;
	}
	/**
	 * @return the arrivalTimeList
	 */
	public List<String> getArrivalTimeList() {
		return arrivalTimeList;
	}
	/**
	 * @param arrivalTimeList the arrivalTimeList to set
	 */
	public void setArrivalTimeList(List<String> arrivalTimeList) {
		this.arrivalTimeList = arrivalTimeList;
	}
	/**
	 * @return the amenitiesList
	 */
	public List<BusAmenity> getAmenitiesList() {
		return amenitiesList;
	}
	/**
	 * @param amenitiesList the amenitiesList to set
	 */
	public void setAmenitiesList(List<BusAmenity> amenitiesList) {
		this.amenitiesList = amenitiesList;
	}
	/**
	 * @return the busOperators
	 */
	public List<String> getBusOperators() {
		return busOperators;
	}
	/**
	 * @param busOperators the busOperators to set
	 */
	public void setBusOperators(List<String> busOperators) {
		this.busOperators = busOperators;
	}
}
