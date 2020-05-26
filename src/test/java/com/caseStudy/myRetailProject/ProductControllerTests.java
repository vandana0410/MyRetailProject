package com.caseStudy.myRetailProject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.caseStudy.myRetailProject.controller.ProductController;
import com.caseStudy.myRetailProject.model.CurrentPrice;
import com.caseStudy.myRetailProject.model.Product;
import com.caseStudy.myRetailProject.model.ProductApiResponse;
import com.caseStudy.myRetailProject.service.ProductService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProductController.class)

@WebMvcTest(value = ProductController.class)

public class ProductControllerTests {

	private static final Logger logger = LoggerFactory.getLogger(ProductControllerTests.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	private Product mockProduct;

	@MockBean
	private ProductApiResponse mockProductApiResponse;
	@MockBean
	private ProductService productService;

	@Before
	public void setup() {
		mockProduct = new Product();
		mockProduct.setProductId(13860428);
		CurrentPrice currentPrice = new CurrentPrice(13.49, "USD");
		mockProduct.setCurrentPrice(currentPrice);
		mockProductApiResponse = new ProductApiResponse(13860428, "The Big Lebowski (Blu-ray)", currentPrice);
	}

	@Test
	public void getProductTestValidTest() throws Exception {

		logger.info("Executing getProductTestValidTest()");

		Mockito.when(productService.getProductById(mockProduct.getProductId())).thenReturn(mockProductApiResponse);

		logger.info("Response from getProductById " + mockProductApiResponse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/" + mockProduct.getProductId())
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		String expectedResult = "{\"productId\":13860428,\"productName\":\"The Big Lebowski (Blu-ray)\",\"currentPrice\":{\"value\":13.49,\"currencyCode\":\"USD\"}}";

		logger.info("expected result = " + expectedResult);

		logger.info("actual result = " + mvcResult.getResponse().getContentAsString());
		JSONAssert.assertEquals(expectedResult, mvcResult.getResponse().getContentAsString(), true);

	}

	@Test
	public void getProductTestInvalidTest() throws Exception {

		mockProduct = new Product();
		mockProduct.setProductId(99999999);
		CurrentPrice currentPrice = new CurrentPrice(44.49, "USD");
		mockProduct.setCurrentPrice(currentPrice);
		mockProductApiResponse = new ProductApiResponse(99999999, "", currentPrice);

		logger.info("Executing getProductTestInvalidTest()");

		Mockito.when(productService.getProductById(mockProduct.getProductId())).thenReturn(mockProductApiResponse);

		logger.info("Response from getProductById " + mockProductApiResponse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/" + mockProduct.getProductId())
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

		String expectedResult = "{\"productId\":99999999,\"productName\":\"\",\"currentPrice\":{\"value\":44.49,\"currencyCode\":\"USD\"}}";

		logger.info("expected result = " + expectedResult);

		logger.info("actual result = " + mvcResult.getResponse().getContentAsString());
		JSONAssert.assertEquals(expectedResult, mvcResult.getResponse().getContentAsString(), false);

	}

}
