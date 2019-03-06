package com.digital.model;

import java.io.Serializable;

/**
 * @author Satyam Kumar
 *
 */
public class BusDetails implements Serializable{

	private static final long serialVersionUID = -6508045063085127065L;
	private String busId;
	private String ownerId;
	private String travelsName;
	private String routeId;
	private String routeName;
	private String distance;
	private String busType;
	private String busTypeName;
	private String bookingDate;
	private String sourceStationId;
	private String destinationStationId;
	private String journeyDate;
	private boolean isAc;
	private String chartCode;
	/**
	 * @return the busId
	 */
	public String getBusId() {
		return busId;
	}
	/**
	 * @param busId the busId to set
	 */
	public void setBusId(String busId) {
		this.busId = busId;
	}
	/**
	 * @return the ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}
	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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
	 * @return the routeName
	 */
	public String getRouteName() {
		return routeName;
	}
	/**
	 * @param routeName the routeName to set
	 */
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	/**
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(String distance) {
		this.distance = distance;
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
	 * @return the busTypeName
	 */
	public String getBusTypeName() {
		return busTypeName;
	}
	/**
	 * @param busTypeName the busTypeName to set
	 */
	public void setBusTypeName(String busTypeName) {
		this.busTypeName = busTypeName;
	}
	/**
	 * @return the bookingDate
	 */
	public String getBookingDate() {
		return bookingDate;
	}
	/**
	 * @param bookingDate the bookingDate to set
	 */
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	/**
	 * @return the sourceStationId
	 */
	public String getSourceStationId() {
		return sourceStationId;
	}
	/**
	 * @param sourceStationId the sourceStationId to set
	 */
	public void setSourceStationId(String sourceStationId) {
		this.sourceStationId = sourceStationId;
	}
	/**
	 * @return the destinationStationId
	 */
	public String getDestinationStationId() {
		return destinationStationId;
	}
	/**
	 * @param destinationStationId the destinationStationId to set
	 */
	public void setDestinationStationId(String destinationStationId) {
		this.destinationStationId = destinationStationId;
	}
	/**
	 * @return the journeyDate
	 */
	public String getJourneyDate() {
		return journeyDate;
	}
	/**
	 * @param journeyDate the journeyDate to set
	 */
	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
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
	 * @return the chartCode
	 */
	public String getChartCode() {
		return chartCode;
	}
	/**
	 * @param chartCode the chartCode to set
	 */
	public void setChartCode(String chartCode) {
		this.chartCode = chartCode;
	}
     
}
