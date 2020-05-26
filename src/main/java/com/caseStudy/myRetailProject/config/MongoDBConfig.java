package com.caseStudy.myRetailProject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import com.caseStudy.myRetailProject.model.CurrentPrice;
import com.caseStudy.myRetailProject.model.Product;
import com.caseStudy.myRetailProject.repository.ProductRepository;

/**
 * @author vandana
 * 
 * 
 *         This class is used to initialize the Mongo DB with data
 */
@EnableMongoRepositories(basePackages = "com.caseStudy.myRetailProject.repository")
@Component
public class MongoDBConfig implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(MongoDBConfig.class);

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) {

		logger.info("Initializing the products Database with data");

		// check for product repository

		if (productRepository != null) {

			// delete all the existing data in the db

			this.productRepository.deleteAll();

			CurrentPrice cp1 = new CurrentPrice(13.49, "USD");

			Product product1 = new Product(13860428, cp1);

			productRepository.save(product1);

			CurrentPrice cp2 = new CurrentPrice(23.45, "USD");

			Product product2 = new Product(13860420, cp2);

			productRepository.save(product2);

			CurrentPrice cp3 = new CurrentPrice(5.99, "USD");

			Product product3 = new Product(12959733, cp3);

			productRepository.save(product3);

			logger.info("Products DB initialized with " + productRepository.count() + " products");
		}

	}

}
