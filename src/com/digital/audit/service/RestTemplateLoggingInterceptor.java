package com.digital.audit.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.digital.audit.dao.ServiceApiResponseLogDao;
import com.digital.payment.model.ServiceApiAuditLog;
import com.digital.utils.GlobalConstants;

@Component
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger log = LoggerFactory.getLogger(RestTemplateLoggingInterceptor.class);

	@Autowired
	private ServiceApiResponseLogDao restLogRepository;

	@Autowired
	private DBLoggingHandler dbLoggingHandler;

	@Override
	public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
			final ClientHttpRequestExecution execution) throws IOException {
		final ServiceApiAuditLog restLog = new ServiceApiAuditLog();
		log.debug(execution.getClass().getName());
		traceRequest(request, body);
		populateRequest(restLog, request, body);
		ClientHttpResponse response = null;
		restLog.setStartTime(new Date());
		try {
			response = execution.execute(request, body);
		} catch (final IOException t) {
			dbLoggingHandler.logError(t.getMessage());
			throw t;
		} finally {
			restLog.setEndTime(new Date());
			restLog.setDurationMilliSeconds(restLog.getEndTime().getTime() - restLog.getStartTime().getTime());
			traceResponse(response);
			populateResponse(restLog, response);
			auditRequest(restLog);
		}

		return response;
	}

	private String getBody(final String bodyString) {
		if (bodyString == null || bodyString.length() == 0) {
			return null;
		} else {
			return bodyString;
		}
	}

	private void populateRequest(final ServiceApiAuditLog restLog, final HttpRequest request, final byte[] body)
			throws IOException {
		restLog.setApiName(getApiName(request));
		restLog.setRequestBody(getRequestBody(body));
		restLog.setRequestUrl(getRequestUrl(request));
		restLog.setRequestHttpMethod(getRequestType(request));
		restLog.setRequestHeader(getRequestHeaders(request));
	}

	private String getRequestHeaders(final HttpRequest request) {
		if (request != null && request.getHeaders() != null) {
			return request.getHeaders().toString();
		} else {
			return null;
		}
	}

	private String getRequestType(final HttpRequest request) {
		if (request != null && request.getMethod() != null) {
			return request.getMethod().toString();
		} else {
			return null;
		}
	}

	private String getRequestUrl(final HttpRequest request) {
		if (request != null && request.getURI() != null) {
			return request.getURI().toString();
		} else {
			return null;
		}
	}

	private String getRequestBody(final byte[] body) throws UnsupportedEncodingException {
		if (body != null && body.length > 0) {
			return getBody(new String(body, "UTF-8"));
		} else {
			return null;
		}
	}

	private void traceRequest(final HttpRequest request, final byte[] body) throws IOException {
		log.debug("Request URI : {}", request.getURI());
		log.debug("Request method : {}", request.getMethod());
		log.debug("Request body : {}", getRequestBody(body));
	}

	private void populateResponse(final ServiceApiAuditLog restLog, final ClientHttpResponse response)
			throws IOException {
		restLog.setResponseBody(getBody(getBodyString(response)));
		restLog.setResponseHeader(getResponseHeaders(response));
		restLog.setResponseHttpCode(getStatusCode(response));
	}

	private String getResponseHeaders(final ClientHttpResponse response) {
		if (response != null && response.getHeaders() != null) {
			return response.getHeaders().toString();
		} else {
			return null;
		}
	}

	private int getStatusCode(final ClientHttpResponse response) throws IOException {
		if (response != null) {
			return response.getRawStatusCode();
		} else {
			return 0;
		}
	}

	private void traceResponse(final ClientHttpResponse response) throws IOException {
		final String body = getBodyString(response);
		if (response != null) {
			log.debug("response status code: {}", response.getRawStatusCode());
		}
		log.debug("response body : {}", body);
	}

	private String getBodyString(final ClientHttpResponse response) {
		try {
			if (response != null && response.getBody() != null && isReadableResponse(response)) {
				final StringBuilder inputStringBuilder = new StringBuilder();

				try (BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(response.getBody(), "UTF-8"))) {
					String line = bufferedReader.readLine();
					while (line != null) {
						inputStringBuilder.append(line);
						inputStringBuilder.append('\n');
						line = bufferedReader.readLine();
					}
				}
				return inputStringBuilder.toString();
			} else {
				return null;
			}
		} catch (final IOException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	private boolean isReadableResponse(final ClientHttpResponse response) {
		if (response == null || response.getHeaders() == null || response.getHeaders().get("Content-Type") == null) {
			return false;
		}

		for (final String contentType : response.getHeaders().get("Content-Type")) {
			if (isReadableContentType(contentType)) {
				return true;
			}
		}
		return false;
	}

	private boolean isReadableContentType(final String contentType) {
		return contentType != null && (contentType.startsWith("application/json") || contentType.startsWith("text"));
	}

	@Async
	private int auditRequest(final ServiceApiAuditLog restLog) {
		try {
			return restLogRepository.addLog(restLog);
		} catch (final DataAccessException e) {
			log.error("failed to save REST log to database", e);
			dbLoggingHandler.logError("RequestURL :[" + restLog.getRequestUrl() + "] HttpStatusCode :["
					+ restLog.getResponseHttpCode() + "] ResponseTime(msec) :[" + restLog.getDurationMilliSeconds()
					+ "], " + e.getRootCause().toString());
		}
		return 0;

	}

	public String getApiName(final HttpRequest request) {
		String apiName = "Static Api Name";
		List<String> httpHeadersList = request.getHeaders().get(GlobalConstants.REQUEST_HEADER_SERVICE_NAME);
		if (httpHeadersList != null && !httpHeadersList.isEmpty()) {
			apiName = httpHeadersList.get(0);
		}
		return apiName;
	}
}
