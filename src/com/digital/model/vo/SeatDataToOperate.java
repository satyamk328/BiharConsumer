package com.digital.model.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SeatDataToOperate {
	
	private String seatType;
	private String seatNumber;
	private Integer isLowerBerth;
	private Double totalFare;
	private Long seatId;
	private String custName;
	private String gender;
	private String email;	
	private int age;
	private Date bookingTime = new Date();

}
