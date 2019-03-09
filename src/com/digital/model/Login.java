package com.digital.model;

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
	private String id;
	private String uid;
	private String name;
	private String sessionId;
	private Date date = new Date();
	private String address;
	private Date logoutd = new Date();
	private String clientIP;
	private String chost;
	
}
