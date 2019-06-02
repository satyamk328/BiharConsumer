package com.digital.user.model;

import java.io.Serializable;

import com.digital.model.BaseModel;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Satyam Kumar
 *
 */
@Setter
@Getter
public class UserRole extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String roleId;
	private String roleName;
	private String description;
	private boolean isActive;
	
}
