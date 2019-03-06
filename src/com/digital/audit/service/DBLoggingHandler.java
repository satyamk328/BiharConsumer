package com.digital.audit.service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.digital.audit.dao.AuditDao;
import com.digital.model.vo.CentralizedLogsVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DBLoggingHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(DBLoggingHandler.class);

	public static final ObjectMapper MAPPER = new ObjectMapper();

	@Value("${application.name}")
	private String applicationName;

	@Autowired
	private AuditDao auditDao;

	public void handleException(final Throwable throwable, final Method method, final Object... obj) {

		try {
			final CentralizedLogsVO vo = new CentralizedLogsVO();
			vo.setAppName(applicationName);
			vo.setLogLevel("ERROR");
			vo.setLogTimeStamp(new Date());
			vo.setLogMessage(exceptionStacktraceToString(throwable, method, obj));
			auditDao.addCentralizedLog(vo);
		} catch (final Exception e) {
			LOGGER.error("Unable to insert error in to log table : " + throwable.getMessage(), e);
		}

	}

	private String exceptionStacktraceToString(final Throwable throwable, final Method method,
			final Object... parameters) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream ps = new PrintStream(baos);
		ps.append(method.getName());
		ps.append(" Parameters : ");
		if (parameters != null) {
			for (final Object parameter : parameters) {
				try {
					ps.append(MAPPER.writeValueAsString(parameter));
					ps.append(" , ");
				} catch (final JsonProcessingException e) {
					LOGGER.error("JSON Processing Exception {}", e);
				}
			}
		}
		ps.append("\n");
		ps.append(populateRequest());
		ps.append("\n");
		throwable.printStackTrace(ps);
		ps.close();
		return baos.toString();
	}

	public void handleException(final Exception ex) {
		handleException(null, ex);
	}

	public void handleException(final String message, final Exception ex) {
		try {
			final CentralizedLogsVO vo = new CentralizedLogsVO();
			vo.setAppName(applicationName);
			vo.setLogLevel("ERROR");
			vo.setLogTimeStamp(new Date());
			if (StringUtils.isBlank(message)) {
				vo.setLogMessage(exceptionStacktraceToString(ex));
			} else {
				vo.setLogMessage("Message : " + message + "\n" + exceptionStacktraceToString(ex));
			}
			auditDao.addCentralizedLog(vo);
		} catch (final Exception e) {
			LOGGER.error("Unable to insert error in to log table : " + e.getMessage(), e);
		}

	}

	private static String exceptionStacktraceToString(final Exception e) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream ps = new PrintStream(baos);
		ps.append(populateRequest());
		if (e != null) {
			e.printStackTrace(ps);
		}
		ps.close();
		return baos.toString();
	}

	public void logError(final String message, final Object paltformResponse) {
		logMessage(message, "ERROR", paltformResponse);
	}

	public void logError(final String message) {
		logMessage(message, "ERROR", null);
	}

	public void logInfo(final String message) {
		logMessage(message, "INFO", null);
	}

	public void logMessage(String message, final String logLevel, final Object paltformResponse) {

		try {
			final CentralizedLogsVO vo = new CentralizedLogsVO();
			vo.setAppName(applicationName);
			vo.setLogLevel(logLevel);
			vo.setLogTimeStamp(new Date());
			if (paltformResponse != null) {
				try {
					message += MAPPER.writeValueAsString(paltformResponse);
				} catch (final Exception e) {
					LOGGER.debug("Unable to add platform repsonse to response", e);
				}
			}

			vo.setLogMessage(message);

			auditDao.addCentralizedLog(vo);
		} catch (final Exception e) {
			LOGGER.error("Unable to insert message in to log table : " + message, e);
		}
	}

	private static String getHeaders(final HttpServletRequest request) {

		if (request.getHeaderNames() != null) {
			String headers = "";
			final Enumeration<String> enumString = request.getHeaderNames();
			while (enumString.hasMoreElements()) {

				final Enumeration<String> headerEnum = request.getHeaders(enumString.nextElement());
				String headersss = "";
				while (headerEnum.hasMoreElements()) {
					headersss = headersss + headerEnum.nextElement().toString();
				}
				headers = headers + headersss;
			}
			while (enumString.hasMoreElements()) {
			}
			return headers;
		}
		return null;
	}

	private static String getRequestBody(final HttpServletRequest request) {
		return "";
	}

	private static HttpServletRequest getHttpServletRequest() {

		ServletRequestAttributes servletRequest = null;
		try {
			servletRequest = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		} catch (final Exception e) {
			LOGGER.debug("Unable to get Request Object", e);
		}
		if (servletRequest == null) {
			return null;
		}
		return servletRequest.getRequest();
	}

	public static String populateRequest() {
		final HttpServletRequest request = getHttpServletRequest();
		if (request == null) {
			return "";
		}
		final StringBuffer buffer = new StringBuffer();

		try {
			buffer.append("\n BODY: " + getRequestBody(request));
		} catch (final Exception e) {
			LOGGER.debug("Unable to get request Body", e);
		}

		try {
			buffer.append("\n METHOD: " + request.getMethod());
		} catch (final Exception e) {
			LOGGER.debug("Unable to get request Method", e);
		}

		try {
			buffer.append("\n URL: " + request.getRequestURL());
		} catch (final Exception e) {
			LOGGER.debug("Unable to get request Url", e);
		}

		try {
			buffer.append("\nQueryParams: " + request.getQueryString());
		} catch (final Exception e) {
			LOGGER.debug("Unable to get request QueryParams", e);
		}

		try {
			buffer.append("\n HEADER: " + getHeaders(request));
		} catch (final Exception e) {
			LOGGER.debug("Unable to get request Headers", e);
		}
		return buffer.toString();

	}
}
