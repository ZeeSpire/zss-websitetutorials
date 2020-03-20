package com.eSkaVision.eSkaVisionTestingSpringBoot.service;

import com.eSkaVision.eSkaVisionTestingSpringBoot.model.Product;

public interface ProductService {
    Product getProductById(Long id, boolean hasDiscount);
}
