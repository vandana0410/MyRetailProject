package com.caseStudy.myRetailProject.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.caseStudy.myRetailProject.exceptions.ProductNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vandana
 * This class calls the external service to get the product name for the product id
 */
@Service
public class ProductExtApiService {

	private static final Logger logger = LoggerFactory.getLogger(ProductExtApiService.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${product.service.api.endpoint}")
	private String productServiceApiEndpoint;

	@Value("${product.service.api.uri}")
	private String productServiceURI;

	@Value("${product.service.api.excludeParam}")
	private String productServiceExcludeParam;

	public String getProductName(Integer id) {

		String apiURL = productServiceApiEndpoint + productServiceURI + id + productServiceExcludeParam;
		String productName = "";

		logger.info("The external product API URL is : " + apiURL);
		try {
			logger.info("Calling external product Api");
			String apiResponse = restTemplate.getForObject(apiURL, String.class);
			logger.info("Response from service = " + apiResponse);

			if (apiResponse != null && apiResponse != "") {

				ObjectMapper mapper = new ObjectMapper();
				JSONObject jsonObject = new JSONObject(apiResponse);

				if (jsonObject.getJSONObject("product").getJSONObject("item")
						.getJSONObject("product_description") != null) {
					JSONObject productDescription = jsonObject.getJSONObject("product").getJSONObject("item")
							.getJSONObject("product_description");
					productName = productDescription.getString("title");
					logger.info("ProductName = " + productName);

				}
			} else {
				logger.info("No response received from external api");
				throw new ProductNotFoundException("No response received from external api");
			}
		} catch (RestClientException e) {
			logger.info("External Service is unavailable");
			throw new RestClientException("External Service is unavailable");

		}

		return productName;
	}

}
