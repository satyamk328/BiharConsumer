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
public class UserModule extends BaseModel implements Serializable {
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
	
}
