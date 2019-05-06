package com.digital.user.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Satyam Kumar
 *
 */
@Setter
@Getter
public class Login implements Serializable{
	
	private static final long serialVersionUID = 3576009501244716303L;
	private Long loginId;
	private Long userId;
	private String userName;
	private String sessionId;
	private Date loginDate;
	private String address;
	private String city;
	private String state;
	private Date logoutDate;
	private String clientIp;
	private String clientHost;
	
}
