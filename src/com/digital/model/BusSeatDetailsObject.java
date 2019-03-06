package com.digital.model;

import java.io.Serializable;
import java.util.List;
/**
 * @author Satyam Kumar
 *
 */
public class BusSeatDetailsObject implements Serializable{

	private static final long serialVersionUID = 8700716073667499536L;
	private List<BusSeatDetails> busSeatDetails;
	private List<BusBoadingStopingDetails> boardingPoints;
	private List<BusBoadingStopingDetails> droppingPoints;
	private double maxSeatsAllowed = 5;
	/**
	 * @return the busSeatDetails
	 */
	public List<BusSeatDetails> getBusSeatDetails() {
		return busSeatDetails;
	}
	/**
	 * @param busSeatDetails the busSeatDetails to set
	 */
	public void setBusSeatDetails(List<BusSeatDetails> busSeatDetails) {
		this.busSeatDetails = busSeatDetails;
	}
	/**
	 * @return the boardingPoints
	 */
	public List<BusBoadingStopingDetails> getBoardingPoints() {
		return boardingPoints;
	}
	/**
	 * @param boardingPoints the boardingPoints to set
	 */
	public void setBoardingPoints(List<BusBoadingStopingDetails> boardingPoints) {
		this.boardingPoints = boardingPoints;
	}
	/**
	 * @return the droppingPoints
	 */
	public List<BusBoadingStopingDetails> getDroppingPoints() {
		return droppingPoints;
	}
	/**
	 * @param droppingPoints the droppingPoints to set
	 */
	public void setDroppingPoints(List<BusBoadingStopingDetails> droppingPoints) {
		this.droppingPoints = droppingPoints;
	}
	/**
	 * @return the maxSeatsAllowed
	 */
	public double getMaxSeatsAllowed() {
		return maxSeatsAllowed;
	}
	/**
	 * @param maxSeatsAllowed the maxSeatsAllowed to set
	 */
	public void setMaxSeatsAllowed(double maxSeatsAllowed) {
		this.maxSeatsAllowed = maxSeatsAllowed;
	}
}
