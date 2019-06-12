package com.digital.spring.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CCavenue implements Serializable{

	private static final long serialVersionUID = 1L;
	private String encrypted;
	private String accessCode;
}
