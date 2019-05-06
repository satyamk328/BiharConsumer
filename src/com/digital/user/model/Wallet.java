package com.digital.user.model;

import java.io.Serializable;

import com.digital.model.BaseModel;

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
	private Long walletId;
	private Long userId;
	private double previousBalance = 0;
	private double currentBalance = 0;
	private double addedBalance = 0;
	private String transactionId;
	private String description;
	private String paymentMode;
	private String senderName;
	
}
