package com.eSkaVision.eSkaVisionTestingSpringBoot.unit.repository;

import com.eSkaVision.eSkaVisionTestingSpringBoot.model.Product;
import com.eSkaVision.eSkaVisionTestingSpringBoot.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
// @ExtendWith(SpringExtension.class) - Register as a Spring Unit test
@DataJpaTest
// Annoation will lunch a H2 database for testing purpose
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void getProductById_Should_ReturnProduct_When_ProperIdIsProvided() {
        productRepository.save(new Product(1L, "Beer", 100.0, 100L));
        productRepository.save(new Product(2L, "Cheese", 200.0, 200L));

        Product cheese = productRepository.getProductById(2L);

        assertEquals(200L, cheese.getQuantity());
    }

    @Test
    void getProductById_Should_ReturnNull_When_ProductIsMissing() {
        Product cheese = productRepository.getProductById(2L);
        assertEquals(null, cheese);
    }
}
