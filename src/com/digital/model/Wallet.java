package com.digital.model;

import java.io.Serializable;
/**
 * @author Satyam Kumar
 *
 */
public class Wallet extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 7198033653493021478L;
	private String id;
	private String userId;
	private double previousBalance = 0;
	private double currentBalance = 0;
	private double addedBalance = 0;
	private String remark;
	private String txId;
	private String paymentMode;
	private String senderName;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the previousBalance
	 */
	public double getPreviousBalance() {
		return previousBalance;
	}
	/**
	 * @param previousBalance the previousBalance to set
	 */
	public void setPreviousBalance(double previousBalance) {
		this.previousBalance = previousBalance;
	}
	/**
	 * @return the currentBalance
	 */
	public double getCurrentBalance() {
		return currentBalance;
	}
	/**
	 * @param currentBalance the currentBalance to set
	 */
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	/**
	 * @return the addedBalance
	 */
	public double getAddedBalance() {
		return addedBalance;
	}
	/**
	 * @param addedBalance the addedBalance to set
	 */
	public void setAddedBalance(double addedBalance) {
		this.addedBalance = addedBalance;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the txId
	 */
	public String getTxId() {
		return txId;
	}
	/**
	 * @param txId the txId to set
	 */
	public void setTxId(String txId) {
		this.txId = txId;
	}
	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}
	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	/**
	 * @return the senderName
	 */
	public String getSenderName() {
		return senderName;
	}
	/**
	 * @param senderName the senderName to set
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
}
