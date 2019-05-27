package com.digital.model.vo;
import java.util.Date;
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
	private Boolean isAC;
	private String boadingPoint;
	private String droppingPoint;
	private String arrivalDate;
	private String arrivalTime;
	private String departureDate;
	private String departureTime;
	private Date bookingDate = new Date();
	private Boolean isLicence = false;
	private Long phone;
	private String email;
	private List<SeatDataToOperate> seatDataToOperate;
}
