package com.eSkaVision.eSkaVisionTestingSpringBoot.unit.service;

import com.eSkaVision.eSkaVisionTestingSpringBoot.model.Product;
import com.eSkaVision.eSkaVisionTestingSpringBoot.repository.ProductRepository;
import com.eSkaVision.eSkaVisionTestingSpringBoot.service.ProductService;
import com.eSkaVision.eSkaVisionTestingSpringBoot.service.ProductServiceImpl;
import com.eSkaVision.eSkaVisionTestingSpringBoot.util.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// We are going to unit test Services like regular Java objects not as Spring beans
@ExtendWith(MockitoExtension.class)
public class ProductSeviceTest {

    @InjectMocks
    private ProductService productService = new ProductServiceImpl();

    @Mock
    private ProductRepository productRepository;

    @Test
    void getProductById_Should_ReturnProduct_When_ParametersAreValidAndDiscountIsApplied(){
        when(productRepository.getProductById(any())).thenReturn(new Product(1L, "Beer", 100.0, 100L));

        Product product = productService.getProductById(1L, true);

        assertEquals("Beer", product.getName());
        assertEquals(90.0, product.getPrice());
        verify(productRepository, times(1)).getProductById(any());
    }

    @Test
    void getProductById_Should_ReturnProduct_When_ParametersAreValidAndDiscountIsNotApplied(){
        when(productRepository.getProductById(any())).thenReturn(new Product(1L, "Beer", 100.0, 100L));

        Product product = productService.getProductById(1L, false);

        assertEquals("Beer", product.getName());
        assertEquals(100.0, product.getPrice());
        verify(productRepository, times(1)).getProductById(any());
    }

    @Test
    void getProductById_Should_ThrowException_When_ProductIsNotFound(){
        assertThrows(ProductNotFoundException.class, () -> {
            when(productRepository.getProductById(any())).thenReturn(null);

            Product product = productService.getProductById(1L, true);
        });
    }
}
