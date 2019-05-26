package com.digital.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Satyam Kumar
 *
 */
@Setter
@Getter
public class ScheduleSeatDetails implements Serializable {

	private static final long serialVersionUID = 8700716073667499536L;
	private List<SeatDetails> seatDetails = new ArrayList<SeatDetails>();
	private List<SeatDetails> lowerSeatDetails = new ArrayList<SeatDetails>();
	private List<SeatDetails> upperSeatDetails = new ArrayList<SeatDetails>();
	private List<CityStop> boardingPoints = new ArrayList<>();
	private List<CityStop> droppingPoints = new ArrayList<>();
	private int maxSeatsAllowed =5;
}
