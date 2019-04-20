package com.digital.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Satyam Kumar
 *
 */
@Setter
@Getter
public class BusCityStopLocationsDetails implements Serializable {

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
	
}
