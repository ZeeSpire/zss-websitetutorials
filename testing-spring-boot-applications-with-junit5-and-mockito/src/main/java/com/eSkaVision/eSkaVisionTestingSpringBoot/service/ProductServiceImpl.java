package com.eSkaVision.eSkaVisionTestingSpringBoot.service;

import com.eSkaVision.eSkaVisionTestingSpringBoot.model.Product;
import com.eSkaVision.eSkaVisionTestingSpringBoot.repository.ProductRepository;
import com.eSkaVision.eSkaVisionTestingSpringBoot.util.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(Long id, boolean hasDiscount) {
        Product product = productRepository.getProductById(id);
        if (product != null) {
            if (hasDiscount) {
                product.setPrice(product.getPrice() - 10);
            }
            return product;
        } else {
            throw new ProductNotFoundException();
        }
    }
}
