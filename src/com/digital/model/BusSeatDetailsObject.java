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
public class BusSeatDetailsObject implements Serializable{

	private static final long serialVersionUID = 8700716073667499536L;
	private List<BusSeatDetails> busSeatDetails;
	private List<BusBoadingStopingDetails> boardingPoints;
	private List<BusBoadingStopingDetails> droppingPoints;
	private double maxSeatsAllowed = 5;
	
}
