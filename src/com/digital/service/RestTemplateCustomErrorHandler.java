package com.digital.service;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

@Component
public class RestTemplateCustomErrorHandler extends DefaultResponseErrorHandler {

	@Override
	public boolean hasError(final ClientHttpResponse response) throws IOException {
		return false;
	}

}
