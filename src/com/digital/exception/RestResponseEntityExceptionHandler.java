package com.digital.exception;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.mail.MailException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.digital.service.MailService;
import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;
import com.digital.utils.GlobalConstants;
/**
 * @author Satyam Kumar
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	@Autowired
    private MailService emailService;
    
	@ExceptionHandler(javax.persistence.EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.NOT_FOUND.value()), ex.getLocalizedMessage()));
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
			WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		if (ex.getCause() instanceof ConstraintViolationException) {
			RestResponse<String> response = new RestResponse<>(null,
					new RestStatus<>(String.valueOf(HttpStatus.CONFLICT.value()), "Database error"));
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
		RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.CONFLICT.value()), ex.getLocalizedMessage()));
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = { MailException.class })
	protected ResponseEntity<Object> mailException(MailException ex, WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage()));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
						"Internal Server Error while performing Operation"));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		final RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()), ex.getMessage()));
		return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

		final RestResponse<String> response = new RestResponse<>(null, new RestStatus<>(
				String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()), builder.substring(0, builder.length() - 2)));
		return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		final RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()), ex.getMessage()));
		return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		final RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage()));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		String error = ex.getParameterName() + " parameter is missing";
		RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.BAD_REQUEST.value()), error));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		final RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage()));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		final RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage()));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.BAD_REQUEST.value()),
						String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
								ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName())));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		String error = "Unable to parse JSON request";
		RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.BAD_REQUEST.value()), error));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		String error = "Error writing JSON output";
		RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), error));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		List<String> errors = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.BAD_REQUEST.value()), errors.toString()));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		final RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage()));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		final RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage()));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		final RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.NOT_FOUND.value()), ex.getMessage()));
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		log.error(ex.getMessage());
		emailService.sendEmail(GlobalConstants.ERROR_HEADER, GlobalConstants.ERROR_EMAIL, GlobalConstants.ERROR_BODY.replaceAll("EEROR_VALUES", ex.getMessage()));
		final RestResponse<String> response = new RestResponse<>(null,
				new RestStatus<>(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()), ex.getMessage()));
		return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
	}
}
