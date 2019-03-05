package com.db.enums;
/**
 * @author Satyam Kumar
 *
 */
public enum PaymentStatus {

	PENDING("Pending"), FAILED("Failed"), SUCCESS("Success");

	String message;

	PaymentStatus(String msg) {
		this.message = msg;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
}
