package com.digital.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Satyam Kumar
 *
 */
@Getter
@Setter
public abstract class BaseModel implements Serializable {

	protected static final long serialVersionUID = 1L;
	private String createdBy;
	private Date dateCreated;
	private String modifiedBy;
	private Date dateModified;

}
