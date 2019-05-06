package com.digital.model.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Satyam Kumar
 *
 */
@Getter
@Setter
public class SearchBusVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String date;
	private String destination;
	private String source;
	
}
