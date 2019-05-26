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
public class SeatDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long seatId;
	private Long busLayoutId;
	private Long rowName;
	private Long columnName;
	private Long width;
	private Long length;
	private String seatType;
	private String seatNumber;
	private String seatName;
	private Boolean isAvailable = false;
	private Boolean isLadiesSeat= false;
	private Boolean isLowerBerth= false;
	private Boolean isReservedForLadies= false;
	private Double fare;
	private Double serviceTaxPercent;
	private Boolean isBooked = false;
	
}
