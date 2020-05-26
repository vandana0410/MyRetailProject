package com.caseStudy.myRetailProject.model;

import org.springframework.stereotype.Component;

@Component
public class ProductApiResponse {

	private Integer productId;

	private String productName;

	private CurrentPrice currentPrice;

	public ProductApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductApiResponse(Integer productId, String productName, CurrentPrice currentPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.currentPrice = currentPrice;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public CurrentPrice getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(CurrentPrice currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "ProductApiResponse [productId=" + productId + ", productName=" + productName + ", currentPrice="
				+ currentPrice + "]";
	}

}
