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
	
}
