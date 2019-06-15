package com.digital.user.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Contact implements Serializable{

	private static final long serialVersionUID = 8137075744514745108L;
	private Long complainId;
	private String senderName;
	private String senderEmail;
	private String phone;
	private String complainType;
	private String description;
	private String status;
	private Boolean isRead = false;
	private Boolean iMPFlag = false;
	private String channelType;
	private Long userId = 0L;
	  
}
