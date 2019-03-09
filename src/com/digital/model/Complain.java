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
public class Complain implements Serializable {
	
	private static final long serialVersionUID = 5387952680835296398L;
	private String refId;
	private String name;
	private long phone;
    private String address;
    private String message;
    private String email;
	
}
