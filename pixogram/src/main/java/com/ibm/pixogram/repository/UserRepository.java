package com.ibm.pixogram.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.pixogram.models.User;






public interface UserRepository extends MongoRepository<User, String> {
	User findByEmail(String email);
	
	
}
