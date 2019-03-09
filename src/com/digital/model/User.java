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
public class User extends BaseModel implements Serializable {

	private static final long serialVersionUID = 8773592091012906066L;
	private String userId;
	private String name;
	private String email;
	private String address;
	private String phoneNumber;
	private String panNumber;
	private String password;
	private String city;
	private String state;
	private String country;
	private boolean isLock = false;
	private Integer attempt;
	private boolean isActive = false;
	private String otp;
	
}