package com.db.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Satyam Kumar
 *
 */
public class Login implements Serializable{
	
	/**
	 * 
	 */
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
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}
	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the logoutd
	 */
	public Date getLogoutd() {
		return logoutd;
	}
	/**
	 * @param logoutd the logoutd to set
	 */
	public void setLogoutd(Date logoutd) {
		this.logoutd = logoutd;
	}
	/**
	 * @return the clientIP
	 */
	public String getClientIP() {
		return clientIP;
	}
	/**
	 * @param clientIP the clientIP to set
	 */
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	/**
	 * @return the chost
	 */
	public String getChost() {
		return chost;
	}
	/**
	 * @param chost the chost to set
	 */
	public void setChost(String chost) {
		this.chost = chost;
	}
	
}
