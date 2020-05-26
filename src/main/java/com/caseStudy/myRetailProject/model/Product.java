package com.caseStudy.myRetailProject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vandana
 * 
 *         This class is used to store the Product Details data
 *
 */
@Document
public class Product {

	@Id
	private Integer productId;

	private CurrentPrice currentPrice;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Integer productId, CurrentPrice currentPrice) {
		super();
		this.productId = productId;
		this.currentPrice = currentPrice;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public CurrentPrice getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(CurrentPrice currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "ProductDetail [productId=" + productId + ", currentPrice=" + currentPrice + "]";
	}

}
