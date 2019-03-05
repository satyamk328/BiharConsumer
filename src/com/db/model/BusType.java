package com.db.model;

import java.io.Serializable;

/**
 * @author Satyam Kumar
 *
 */
public class BusType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6242482537486686384L;
	private int id;
	private String busType;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the busType
	 */
	public String getBusType() {
		return busType;
	}
	/**
	 * @param busType the busType to set
	 */
	public void setBusType(String busType) {
		this.busType = busType;
	}
	
	
}
