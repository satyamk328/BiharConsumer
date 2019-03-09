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
public class BusCancellationPolicies implements Serializable{

	private static final long serialVersionUID = -7267795731820416481L;
	private String ruleId;
	private String busid;
	private String departureheading;
	private String policyheading;
	
}
