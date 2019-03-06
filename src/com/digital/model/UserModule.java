package com.digital.model;

import java.io.Serializable;
/**
 * @author Satyam Kumar
 *
 */
public class UserModule extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roleId;
	private String privilegeName;
	private boolean isRecharge;
	private boolean isBillPayment;
	private boolean isMoneyTransfer;
	private boolean isAddMonry;
	private boolean isExportReport;
	private boolean isBusBook;
	private String userId;
	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the privilegeName
	 */
	public String getPrivilegeName() {
		return privilegeName;
	}
	/**
	 * @param privilegeName the privilegeName to set
	 */
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	/**
	 * @return the isRecharge
	 */
	public boolean isRecharge() {
		return isRecharge;
	}
	/**
	 * @param isRecharge the isRecharge to set
	 */
	public void setRecharge(boolean isRecharge) {
		this.isRecharge = isRecharge;
	}
	/**
	 * @return the isBillPayment
	 */
	public boolean isBillPayment() {
		return isBillPayment;
	}
	/**
	 * @param isBillPayment the isBillPayment to set
	 */
	public void setBillPayment(boolean isBillPayment) {
		this.isBillPayment = isBillPayment;
	}
	/**
	 * @return the isMoneyTransfer
	 */
	public boolean isMoneyTransfer() {
		return isMoneyTransfer;
	}
	/**
	 * @param isMoneyTransfer the isMoneyTransfer to set
	 */
	public void setMoneyTransfer(boolean isMoneyTransfer) {
		this.isMoneyTransfer = isMoneyTransfer;
	}
	/**
	 * @return the isAddMonry
	 */
	public boolean isAddMonry() {
		return isAddMonry;
	}
	/**
	 * @param isAddMonry the isAddMonry to set
	 */
	public void setAddMonry(boolean isAddMonry) {
		this.isAddMonry = isAddMonry;
	}
	/**
	 * @return the isExportReport
	 */
	public boolean isExportReport() {
		return isExportReport;
	}
	/**
	 * @param isExportReport the isExportReport to set
	 */
	public void setExportReport(boolean isExportReport) {
		this.isExportReport = isExportReport;
	}
	/**
	 * @return the isBusBook
	 */
	public boolean isBusBook() {
		return isBusBook;
	}
	/**
	 * @param isBusBook the isBusBook to set
	 */
	public void setBusBook(boolean isBusBook) {
		this.isBusBook = isBusBook;
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
	
}
