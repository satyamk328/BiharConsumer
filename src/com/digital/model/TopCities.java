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
public class TopCities implements Serializable {
	private static final long serialVersionUID = -950330307004124256L;
	private long cityid;
	private String displayName;
	private String cityName;
	private String stateName;
	private String country;
	private String district;

}