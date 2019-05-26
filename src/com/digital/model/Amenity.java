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
public class Amenity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7081507500727075917L;
	private String amenityId;
	private String name;
	private String label;
	private String icon;
	
}
