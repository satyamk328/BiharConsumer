package com.db.model.vo;

import java.io.Serializable;
/**
 * @author Satyam Kumar
 *
 */
public class SearchTripVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1860227643588269105L;
	private String journeyDate;
	private String operatorId;
	private String providerId;
	private String tripId;
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
	 * @return the tripId
	 */
	public String getTripId() {
		return tripId;
	}
	/**
	 * @param tripId the tripId to set
	 */
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
}
