package com.digital.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Satyam Kumar
 *
 */
@Setter
@Getter
public class ScheduleBusObject implements Serializable{
	
	private static final long serialVersionUID = 1068912061763283486L;
	private List<ScheduleBusDetails> availableRoutes = new ArrayList<>();
	private List<String> departureTimeList = new ArrayList<>();
	private List<String> arrivalTimeList = new ArrayList<>();
	private List<Amenity> amenitiesList = new ArrayList<>();
	private List<String> busOperators = new ArrayList<>();
	
}
