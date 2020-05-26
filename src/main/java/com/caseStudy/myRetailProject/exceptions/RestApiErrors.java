package com.caseStudy.myRetailProject.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RestApiErrors {

	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String detailMessage;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

	private RestApiErrors() {
		timestamp = LocalDateTime.now();
	}

	public RestApiErrors(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.detailMessage = ex.getLocalizedMessage();
	}

	public RestApiErrors(HttpStatus status) {
		this();
		this.status = status;
	}

	public RestApiErrors(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.detailMessage = ex.getLocalizedMessage();
	}

}
