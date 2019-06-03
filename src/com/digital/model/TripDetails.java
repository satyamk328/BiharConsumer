package com.digital.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripDetails implements Serializable{

	private static final long serialVersionUID = 1702719370829844659L;
	private Long tripId;
	private Long scheduleId;
	private Long cityId;
	private Integer citySequance;
	private String cityStops;
	private String arrivalDate;
	private String arrivalTime;
	private String baseFare;
	private Long distance;
	
}
