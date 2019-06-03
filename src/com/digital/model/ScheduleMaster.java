package com.digital.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScheduleMaster {

	private Long scheduleId;
	private Long busId;
	private Long sourceCityId;
	private Long destinationCityId;
	private String departureDate;
	private String departureTime;
	private String arrivalDate;
	private String arrivalTime;
	private Double sleeperFare;
	private Double seaterFare;
	private Boolean isFixed;
	private Double baseFare;

}
