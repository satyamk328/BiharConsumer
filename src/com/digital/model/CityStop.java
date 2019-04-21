package com.digital.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityStop {

	private Long cityStopId;
	private Long cityId;
	private String locationName;
	private String locationAddress;
	private String landMark;
	private double lat;
	private double lng;
	
}
