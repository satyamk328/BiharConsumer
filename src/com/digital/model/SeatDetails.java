package com.digital.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SeatDetails {

	private Long seatId;
	private Long busLayoutId;
	private String rowName;
	private String columnName;
	private Long width;
	private Long length;
	private String seatType;
	private String seatName;
	private String seatNumber;
	private int isAvailable;
	private int isLadiesSeat;
	private int isLowerBerth;
	private int isBooked;
}
