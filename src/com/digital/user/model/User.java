package com.digital.user.model;

import java.io.Serializable;

import com.digital.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Long userId;
	private Long roleId;
	private String name;
	private String email;
	private String address;
	private Long phoneNumber;
	private String panNumber;
	@JsonIgnore(true)
	private String password;
	private String city;
	private String state;
	private String country;
	private boolean isLock;
	private Integer attempt;
	private boolean isActive;
	private String otp;
	
}