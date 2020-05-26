package com.caseStudy.myRetailProject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.caseStudy.myRetailProject.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

	public Product findProductByProductId(Integer id);

}
