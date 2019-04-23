package com.digital.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketDetails {
	private Long ticketId;
	private Long scheduleId;
	private Long userId;
	private Long seatId;
	private String tripId;
}
