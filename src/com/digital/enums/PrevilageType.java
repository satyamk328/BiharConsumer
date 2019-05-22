package com.digital.enums;

public enum PrevilageType {
	ADMIN(1), CUSTOMER(3), RETAILER(2);

	public int key;

	PrevilageType(int key) {
		this.key = key;
	}

}
