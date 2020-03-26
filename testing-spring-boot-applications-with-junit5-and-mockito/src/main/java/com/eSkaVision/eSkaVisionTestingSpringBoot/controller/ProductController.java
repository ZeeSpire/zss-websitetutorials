package com.eSkaVision.eSkaVisionTestingSpringBoot.controller;

import com.eSkaVision.eSkaVisionTestingSpringBoot.model.Product;
import com.eSkaVision.eSkaVisionTestingSpringBoot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/status")
    public String checkStatus(){
        return "Live!";
    }

    @GetMapping("/products")
    public Product getProduct(@RequestParam Long id){
        boolean discount = true;
        return productService.getProductById(id, discount);
    }
}
