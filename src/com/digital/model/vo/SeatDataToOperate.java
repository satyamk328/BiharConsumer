package com.digital.model.vo;

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
}
