package com.db.model;

import java.io.Serializable;
/**
 * @author Satyam Kumar
 *
 */
public class BusSeatDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	private int row;
	private int column;
	private int width;
	private int length;
	private String layoutId;
	private String seatType;
	private String seatNumber;
	private String seatName;
	private boolean isAvailable;
	private boolean isLadiesSeat;
	private boolean isMenSeat;
	private boolean isLowerBerth;
	private boolean isReservedForLadies;
	private double fare;
	private double serviceTaxPercent;
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	/**
	 * @return the layoutId
	 */
	public String getLayoutId() {
		return layoutId;
	}
	/**
	 * @param layoutId the layoutId to set
	 */
	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}
	/**
	 * @return the seatType
	 */
	public String getSeatType() {
		return seatType;
	}
	/**
	 * @param seatType the seatType to set
	 */
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	/**
	 * @return the seatNumber
	 */
	public String getSeatNumber() {
		return seatNumber;
	}
	/**
	 * @param seatNumber the seatNumber to set
	 */
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	/**
	 * @return the seatName
	 */
	public String getSeatName() {
		return seatName;
	}
	/**
	 * @param seatName the seatName to set
	 */
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	/**
	 * @return the isAvailable
	 */
	public boolean isAvailable() {
		return isAvailable;
	}
	/**
	 * @param isAvailable the isAvailable to set
	 */
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	/**
	 * @return the isLadiesSeat
	 */
	public boolean isLadiesSeat() {
		return isLadiesSeat;
	}
	/**
	 * @param isLadiesSeat the isLadiesSeat to set
	 */
	public void setLadiesSeat(boolean isLadiesSeat) {
		this.isLadiesSeat = isLadiesSeat;
	}
	/**
	 * @return the isMenSeat
	 */
	public boolean isMenSeat() {
		return isMenSeat;
	}
	/**
	 * @param isMenSeat the isMenSeat to set
	 */
	public void setMenSeat(boolean isMenSeat) {
		this.isMenSeat = isMenSeat;
	}
	/**
	 * @return the isLowerBerth
	 */
	public boolean isLowerBerth() {
		return isLowerBerth;
	}
	/**
	 * @param isLowerBerth the isLowerBerth to set
	 */
	public void setLowerBerth(boolean isLowerBerth) {
		this.isLowerBerth = isLowerBerth;
	}
	/**
	 * @return the isReservedForLadies
	 */
	public boolean isReservedForLadies() {
		return isReservedForLadies;
	}
	/**
	 * @param isReservedForLadies the isReservedForLadies to set
	 */
	public void setReservedForLadies(boolean isReservedForLadies) {
		this.isReservedForLadies = isReservedForLadies;
	}
	/**
	 * @return the fare
	 */
	public double getFare() {
		return fare;
	}
	/**
	 * @param fare the fare to set
	 */
	public void setFare(double fare) {
		this.fare = fare;
	}
	/**
	 * @return the serviceTaxPercent
	 */
	public double getServiceTaxPercent() {
		return serviceTaxPercent;
	}
	/**
	 * @param serviceTaxPercent the serviceTaxPercent to set
	 */
	public void setServiceTaxPercent(double serviceTaxPercent) {
		this.serviceTaxPercent = serviceTaxPercent;
	}
	
}
