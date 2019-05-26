package com.digital.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Satyam Kumar
 *
 */
@Getter
@Setter
public class CancelPolicies implements Serializable{

	private static final long serialVersionUID = -7267795731820416481L;
	private String policyId;
	private String busId;
	private String departureHeading;
	private String policyHeading;
	
}
