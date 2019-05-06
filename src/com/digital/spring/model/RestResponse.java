package com.digital.spring.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Satyam Kumar
 *
 */
@Getter
@Setter
public class RestResponse<T> {
    
    T data;
    RestStatus<?> status;

	public RestResponse() {
	}

    public RestResponse(final T data, final RestStatus<?> status) {
        this.data = data;
        this.status = status;
    }
    
    public RestResponse(final T data) {
        this.data = data;
    }
	
}