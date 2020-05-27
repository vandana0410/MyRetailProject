# Case Study myRetail RESTful service (Option 1)

### 1.	Introduction

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 
The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 
Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.
Build an application that performs the following actions: 
- 	Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
- 	Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 
- 	Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
- 	Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail)  
- 	Example: http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics
- 	Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.  
- 	BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 

### 2.	Technologies Used
- 	Spring Boot 2.3.0
- 	JDK 1.8
- 	Mongo DB (No SQL DB)
- 	Swagger UI -2.9.2
- 	Junit with Mockito
- 	Maven 4.0


### 3.	Assumptions
- There is no authentication and authorization required to access these RESTful service APIs.
-	This project provides the back-end implementation of the RESTful service APIs. 
-	For accessing these RESTful service API, use the API testing client tools like Postman, Swagger etc.
-	Load testing of these services are not done as part of this case study

### 4.	Installations
-	Install JDK 1.8
-	Install Maven 4.0
-	Install Mongo DB 

### 5.	Setup
-	Git clone repo - https://github.com/vandana0410/MyRetailProject.git 
-	Run mongo db – mongod
-	Go to root directory of cloned MyRetailProject and type the following command on command line (terminal) to run the project:
--	mvn spring-boot:run
-	The application is using port 8090.
-	To run the tests type the following command on the command line (terminal) 
-- mvn test
- 
- Once the application is up, use Swagger to access myRetail APIs.
Swagger UI -- http://localhost:8090/swagger-ui.html#/ 

### 6.	Solution
- Architecture

![Architecture](/images/Architecture.png)


-	Sequence Diagram for HTTP GET Request to get the product for the given product id.

![Get Product Sequence Diagram](/images/GetProductSequenceDiagram.png)


-	Sequence Diagram for HTTP PUT Request to update the price of the given product id.

![Update Product Price Sequence Diagram](/images/UpdateProductSequenceDiagram.png)


### 7.	Sample Requests

- Get All Products 

![Get All Products](/images/GetProductAll.png)

- Get product with valid product id

![Get Valid Product](/images/GetProductValid.png)

- Get product with invalid product id

![Get Invalid Product](/images/GetProductInvalid.png)

- Update Product Price with valid product id

![Update price valid](/images/UpdatePriceValid.png)

- Update Product Price with invalid product id

![Update price invalid](/images/UpdatePriceInvalid.png)



### 7. Future Enhancements

- To make these APIs production we need to
  - Add spring security features so only authorized users can access myRetail service APIs
  - Secure the API in order to avoid DDoS and botnet issues. 
  - Perform load testing and calculate the user load to identify the servers needed to make these APIs available
  - We can add more APIs to extend the functionality and develop a product catalog services that will provide the APIs to get product details based on various paramaters.
  - To improve the performance we use Distributed Cache to read static product details




