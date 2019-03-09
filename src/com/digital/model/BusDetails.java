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
	
}
