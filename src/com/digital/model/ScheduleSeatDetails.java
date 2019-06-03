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
	private List<SeatMaster> busSeatDetails = new ArrayList<SeatMaster>();
	private List<SeatMaster> lowerSeatDetails = new ArrayList<SeatMaster>();
	private List<SeatMaster> upperSeatDetails = new ArrayList<SeatMaster>();
	private List<CityStopMaster> boardingPoints = new ArrayList<>();
	private List<CityStopMaster> droppingPoints = new ArrayList<>();
	private int maxSeatsAllowed =5;
}
