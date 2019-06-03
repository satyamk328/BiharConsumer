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
public class CityMaster implements Serializable{
	
	private static final long serialVersionUID = -2496060421142221098L;
	private long cityId;
	private String displayName;
	private String cityName;
	private String stateName;
	private String country;
	private String district;

}