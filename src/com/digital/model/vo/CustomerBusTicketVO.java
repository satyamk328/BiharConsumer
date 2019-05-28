package com.digital.model.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Satyam Kumar
 *
 */
@Setter
@Getter
public class CustomerBusTicketVO {

	private String bookingid;
	private String userId;
	private String busname;
	private String busnumber;
	private String srccityname;
	private String destcityname;
	private String arrivaldatetime;
	private String departuredatetime;
	private String seattype;
	private double totalfare;
	private String chartstatus;
	private String customername;
	private int age;
	private String email;
	private String phonenumber;
	private boolean islicence;
	private String seatnumber;
		
}
