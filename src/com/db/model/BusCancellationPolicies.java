package com.db.model;

import java.io.Serializable;
/**
 * @author Satyam Kumar
 *
 */
public class BusCancellationPolicies implements Serializable{

	private static final long serialVersionUID = -7267795731820416481L;
	private String ruleId;
	private String busid;
	private String departureheading;
	private String policyheading;
	/**
	 * @return the ruleId
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * @param ruleId the ruleId to set
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * @return the busid
	 */
	public String getBusid() {
		return busid;
	}
	/**
	 * @param busid the busid to set
	 */
	public void setBusid(String busid) {
		this.busid = busid;
	}
	/**
	 * @return the departureheading
	 */
	public String getDepartureheading() {
		return departureheading;
	}
	/**
	 * @param departureheading the departureheading to set
	 */
	public void setDepartureheading(String departureheading) {
		this.departureheading = departureheading;
	}
	/**
	 * @return the policyheading
	 */
	public String getPolicyheading() {
		return policyheading;
	}
	/**
	 * @param policyheading the policyheading to set
	 */
	public void setPolicyheading(String policyheading) {
		this.policyheading = policyheading;
	}
	
}
