package com.db.consumer;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.db.exception.BaseException;

public abstract class BaseService<T> implements IService<T> {
	
	private static final Logger log = Logger.getLogger(BaseService.class);

	public BaseService() {
		super();
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	public void logUrl(String httpVerb, String endPoint, Object...args) {
		if (log.isDebugEnabled()) {
			log.debug(String.format("HttpVerb [%s] URL [%s] Args [%s]", httpVerb, endPoint, Arrays.toString(args)));
		}
	}

	@Override
	public final T execute(String command, BaseHttpMethod httpMethod, Object... params) throws BaseException {
		this.logUrl(httpMethod.toString(), this.getEndpoint() + command, params);
		T returnValue = null;
		try {
			switch (httpMethod) {

			case GET:
				returnValue = this.getGetResponse(command);
				break;

			case POST:
				returnValue = this.getPutOrPostResponse(command, HttpMethod.POST, params);
				break;

			case PUT:
				returnValue = this.getPutOrPostResponse(command, HttpMethod.PUT, params);
				break;

			default: throw new IllegalArgumentException("Illegal httpMethod " + httpMethod + " provided");
			}
			if (log.isDebugEnabled()) {
				log.debug("Response list:" + (returnValue != null ? returnValue.toString() : "N/A"));
			}
		} catch (Exception e) {
			throw new BaseException();
		}

		return returnValue;
	}

	public abstract ParameterizedTypeReference<T> buildParameterizedTypeReference();

	/**
	 * @param command
	 * @return
	 * @throws RestClientException
	 */
	private T getGetResponse(String command) {
		ResponseEntity<T> getResponse = (ResponseEntity<T>) this.getRestTemplate().exchange(this.getEndpoint() + command,
				HttpMethod.GET, HttpEntity.EMPTY, buildParameterizedTypeReference());
		return getResponse.getBody();
	}

	/**
	 * 
	 * @param command
	 * @param method
	 * @param params
	 * @return
	 * @throws RestClientException
	 */
	private T getPutOrPostResponse(String command, HttpMethod method, Object... params) {
		HttpEntity<Object> entity = null;
		if (params != null) {
			entity = params.length == 2 ? new HttpEntity<>(params[0], (HttpHeaders) params[1]) : new HttpEntity<>(params[0]);
		}

		ResponseEntity<T> response = (ResponseEntity<T>) this.getRestTemplate().exchange(this.getEndpoint() + command, method,
				entity, this.buildParameterizedTypeReference());
		return response.getBody();
	}
}
