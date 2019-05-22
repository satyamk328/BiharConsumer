package com.digital.model.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Satyam Kumar
 *
 */

@Setter
@Getter
public class TicketVO {

	private Long scheduleId;
	private Long userId;
	private Long busId;
	
	private Long srcCityId;
	private Long destCityId;
	
	private String tripId;
	private String busType;
	private String travelName;
	private Integer isAC;
	
	
	private List<SeatDataToOperate> seatDataToOperate;
}
