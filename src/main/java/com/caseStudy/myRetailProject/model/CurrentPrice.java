package com.caseStudy.myRetailProject.model;

/**
 * @author vandana
 *
 *         This class is used to store current price for a product.
 */

public class CurrentPrice {

	private Double value;

	private String currencyCode;

	public CurrentPrice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurrentPrice(Double value, String currencyCode) {
		super();
		this.value = value;
		this.currencyCode = currencyCode;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Override
	public String toString() {
		return "CurrentPrice [value=" + value + ", currencyCode=" + currencyCode + "]";
	}

}
