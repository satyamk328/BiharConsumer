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
	private Long cityStopId;
	private Long cityId;
	private String locationName;
	private String locationAddress;
	private String landMark;
	private double lat;
	private double lng;
}
