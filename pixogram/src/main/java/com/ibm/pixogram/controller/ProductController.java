package com.ibm.pixogram.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.pixogram.models.Products;
import com.ibm.pixogram.repository.ProductRepository;
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public Iterable<Products> product() {
        return productRepository.findAll();
    }
}
