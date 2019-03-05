package com.db.model;

import java.io.Serializable;
/**
 * @author Satyam Kumar
 *
 */
public class BusBoadingStopingDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String locationName;
	private String locationAddress;
	private double lat;
	private double lng;
	private String busid;
	private String date;
	private String time;
	private String contactNumber;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}
	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	/**
	 * @return the locationAddress
	 */
	public String getLocationAddress() {
		return locationAddress;
	}
	/**
	 * @param locationAddress the locationAddress to set
	 */
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}
	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
	/**
	 * @return the lng
	 */
	public double getLng() {
		return lng;
	}
	/**
	 * @param lng the lng to set
	 */
	public void setLng(double lng) {
		this.lng = lng;
	}
	/**
	 * @return the busid
	 */
	public String getBusid() {
		return busid;
	}
	/**
	 * @param busid the busid to set
	 */
	public void setBusid(String busid) {
		this.busid = busid;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}
	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
}
