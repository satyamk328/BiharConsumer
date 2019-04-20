package com.digital.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Satyam Kumar
 *
 */
@Setter
@Getter
public class BusSeatDetails implements Serializable{

	private static final long serialVersionUID = 8700716073667499536L;
	private List<BusSeatBookingDetails> busSeatDetails;
	private List<BusCityStopLocationsDetails> boardingPoints;
	private List<BusCityStopLocationsDetails> droppingPoints;
	
}
