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
public class UserRole extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String roleId;
	private String roleName;
	private boolean isRecharge;
	private boolean isBillPayment;
	private boolean isMoneyTransfer;
	private boolean isAddMoney;
	private boolean isExportReport;
	private boolean isBusBook;
	private Long userId;
	
}
