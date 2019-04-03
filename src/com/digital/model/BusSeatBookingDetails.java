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
public class BusSeatBookingDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	private int row;
	private int column;
	private int width;
	private int length;
	private String layoutId;
	private String seatType;
	private String seatNumber;
	private String seatName;
	private boolean isAvailable;
	private boolean isLadiesSeat;
	private boolean isMenSeat;
	private boolean isLowerBerth;
	private boolean isReservedForLadies;
	private double fare;
	private double serviceTaxPercent;
	
}
