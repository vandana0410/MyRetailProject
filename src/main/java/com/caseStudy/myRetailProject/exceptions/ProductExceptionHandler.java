package com.caseStudy.myRetailProject.exceptions;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "HTTP method is not supported";
		return buildResponseEntity(new RestApiErrors(HttpStatus.METHOD_NOT_ALLOWED, error, ex));
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public final ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex,
			WebRequest request) {
		String error = ex.getMessage();
		return buildResponseEntity(new RestApiErrors(HttpStatus.BAD_REQUEST, error, ex));

	}
	

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

		RestApiErrors apiError = new RestApiErrors(HttpStatus.BAD_REQUEST, error,ex);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	
	@ExceptionHandler(RestClientException.class)
	public final ResponseEntity<Object> handleRestClientException(RestClientException ex,
			WebRequest request) {
		String error = ex.getMessage();
		return buildResponseEntity(new RestApiErrors(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));

	}
	
	@ExceptionHandler(ProductException.class)
	public final ResponseEntity<Object> handleProductException(IOException ex,
			WebRequest request) {
		String error = ex.getMessage();
		return buildResponseEntity(new RestApiErrors(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));

	}
	
	@ExceptionHandler(IOException.class)
	public final ResponseEntity<Object> handleIOException(IOException ex,
			WebRequest request) {
		String error = ex.getMessage();
		return buildResponseEntity(new RestApiErrors(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));

	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> exceptionHandler(Exception ex,
			WebRequest request) {
		String error = ex.getMessage();
		return buildResponseEntity(new RestApiErrors(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));

	}
	

	
	private ResponseEntity<Object> buildResponseEntity(RestApiErrors restApiErrors) {
		return new ResponseEntity<>(restApiErrors, restApiErrors.getStatus());
	}

}
