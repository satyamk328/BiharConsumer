package com.digital.model.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SeatDataToOperate {
	
	private String seatType;
	private String seatNumber;
	private Boolean isLowerBerth;
	private Double totalFare;
	private Long seatId;
	private String custName;
	private String gender;
	private String email;	
	private int age;
	private int phone;

}
