package com.caseStudy.myRetailProject.exceptions;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5776681206288518465L;

	public ProductNotFoundException(String message) {
		super(message);
	}
	
	public ProductNotFoundException(String message,Integer id) {
		super( "Product Id: " + id + "is not found.");
	}
}
