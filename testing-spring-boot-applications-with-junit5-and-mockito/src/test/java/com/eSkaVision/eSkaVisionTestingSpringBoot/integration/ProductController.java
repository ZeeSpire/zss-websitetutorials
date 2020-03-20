package com.eSkaVision.eSkaVisionTestingSpringBoot.integration;

import com.eSkaVision.eSkaVisionTestingSpringBoot.model.Product;
import com.eSkaVision.eSkaVisionTestingSpringBoot.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
//    @ExtendWith(SpringExtension.class) - Register as a Spring Unit test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//    @SpringBootTest will lunch the entire Spring Boot application on a random port
//    All components within the application will pe instantiated in Application Context
//    This will simulate the application running as a whole, unlike unit tests which test just individual components
//    Also this will lunch the in-memory database and query it just like in a real scenario
public class ProductController {

    @Autowired
    private TestRestTemplate testRestTemplate;
    // This Component is provided by Spring Boot to be used to make API calls during Integration tests

    @Autowired
    private ProductRepository productRepository;

    // @MockBean
    // private ProductRepository productRepositoryMock;
    // If we want for example to mock on of the components in our application and stop testing it
    // we can use @MockBean and then use when(productRepositoryMock.method(..)).thenReturn(...) in our tests

    @Test
    void checkStatusApi_Should_ReturnString_When_Called() {
        String response = testRestTemplate.getForObject("/status", String.class);
        assertEquals(response, "Live!");
    }

    @Test
    @DirtiesContext //@DirtiesContext will rollback database changes after test is done
    void getProductApi_Should_ReturnProduct_When_Called() {
        Product p1 = new Product("Beer", 110.0, 10L);
        Product p2 = new Product("Cheese", 100.0, 9L);
        Product p3 = new Product("WIne", 110.0, 10L);
        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);

        ResponseEntity<Product> response = testRestTemplate
                .getForEntity("/products?id=1", Product.class, new HashMap<String, String>());

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        assertEquals(response.getBody().getName(), p1.getName());
        assertEquals(response.getBody().getQuantity(), p1.getQuantity());
        assertEquals(response.getBody().getPrice(), p1.getPrice() - 10);
    }
}
