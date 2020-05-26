package com.caseStudy.myRetailProject.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.caseStudy.myRetailProject.exceptions.ProductException;
import com.caseStudy.myRetailProject.exceptions.ProductNotFoundException;
import com.caseStudy.myRetailProject.model.Product;
import com.caseStudy.myRetailProject.model.ProductApiResponse;
import com.caseStudy.myRetailProject.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author vandana
 *
 */
@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductExtApiService productExtApiService;

	/*
	 * This method is used to get all the products from the repository
	 */
	public List<Product> getAll() {
		return productRepository.findAll();

	}

	/*
	 * This method gets the products for the given product id.
	 * 
	 * 
	 */
	public ProductApiResponse getProductById(Integer id) throws ProductException {

		Product product = new Product();

		String productName = "";

		ProductApiResponse productApiResponse = new ProductApiResponse();
		try {
			// find the product id in the repository
			product = productRepository.findProductByProductId(id);

			if (product != null) {
				// if product id is available in the repository then call the external api to
				// get the product name

				productName = productExtApiService.getProductName(id);

				if (productName != null) {
					productApiResponse = setProductName(product, productName);
				} else {
					logger.info("Product Name not available");
				}
			} else {
				logger.info("Product Id:" + id + "is not available in the repository");
				throw new ProductNotFoundException("Product Id:" + id + "is not available in the repository");

			}
		} catch (RestClientException e) {
			logger.info("Error in calling the external api to get the product name");
			throw new RestClientException("Error in calling the external api to get the product name.");
		}

		return productApiResponse;
	}

	private ProductApiResponse setProductName(Product product, String productName) {

		ProductApiResponse productApiResponse = new ProductApiResponse();

		productApiResponse.setProductId(product.getProductId());
		productApiResponse.setCurrentPrice(product.getCurrentPrice());
		productApiResponse.setProductName(productName);

		return productApiResponse;
	}

	/*
	 * This method updates the price of given product id in the repository
	 */
	public Product updatePriceById(Product product, Integer productId) throws ProductException {

		logger.info("Updating the Price of product id" + productId);
		Product currentProduct;
		try {
			currentProduct = productRepository.findProductByProductId(productId);

			if (currentProduct != null) {
				currentProduct.setCurrentPrice(product.getCurrentPrice());
				productRepository.save(currentProduct);
			}
		} catch (ProductException ex) {
			logger.info("Exception occured updating the product price for product id: " + productId);
			throw new ProductNotFoundException(
					"Exception occured updating the product price for product id: " + productId);

		}
		return currentProduct;

	}

}
