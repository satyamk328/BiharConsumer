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
	private String userId;
	private Long busId;
	
	private Long srcCityId;
	private Long destCityId;
	private String srcCityName;
	private String destCityName;
	
	private String tripId;
	private String busType;
	private String pnr;
	private String transactionId;
	private String travelName;
	private Double distance;
	private Boolean isAC;
	private String boadingPoint;
	private String droppingPoint;
	private String arrivalDate;
	private String arrivalTime;
	private String departureDate;
	private String departureTime;
	private Date bookingDate = new Date();
	private Boolean isLicence = false;
	private String phone;
	private String email;
	private Double totalFare;
	private List<SeatDataToOperate> seatDataToOperate;
}
