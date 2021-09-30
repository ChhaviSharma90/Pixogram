package com.ibm.pixogram.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.pixogram.models.Products;

public interface ProductRepository extends MongoRepository<Products, String> {

    @Override
    void delete(Products deleted);
}
