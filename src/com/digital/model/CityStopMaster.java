package com.digital.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityStopMaster implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long cityStopId;
	private Long cityId;
	private String locationName;
	private String locationAddress;
	private String landMark;
	private String lat;
	private String lng;
	private String contactNumber;
	
}
