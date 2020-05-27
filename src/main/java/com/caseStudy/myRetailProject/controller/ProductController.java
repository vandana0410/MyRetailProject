package com.caseStudy.myRetailProject.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caseStudy.myRetailProject.model.Product;
import com.caseStudy.myRetailProject.model.ProductApiResponse;
import com.caseStudy.myRetailProject.service.ProductService;

/**
 * @author vandana
 * This is the controller class.
 */
@RestController
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	/*
	 * To get all the products from the repository
	 */

	@RequestMapping(value = "/products/all", method = RequestMethod.GET, produces = "application/json")
	public List<Product> getAll() {
		logger.info("Get all products");
		return productService.getAll();
	}

	/*
	 * To get the product details along with the product name
	 */
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ProductApiResponse> getProduct(@PathVariable("id") Integer id) {
		logger.info("Get the product details for " + id);
		return new ResponseEntity<ProductApiResponse>(productService.getProductById(id), HttpStatus.OK);
	}

	/*
	 * To update the current price of a given product
	 */
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Product> updateCurrentPrice(@RequestBody Product product,
			@PathVariable("id") Integer productId) {

		Product updateProduct = new Product();
		logger.info("Update the current price for product" + productId);
		updateProduct = productService.updatePriceById(product, productId);

		return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);
	}

}
