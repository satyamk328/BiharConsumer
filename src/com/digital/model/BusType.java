package com.digital.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Satyam Kumar
 *
 */
@Setter
@Getter
public class BusType implements Serializable{
	private static final long serialVersionUID = 6242482537486686384L;
	private int id;
	private String busType;
	
}
