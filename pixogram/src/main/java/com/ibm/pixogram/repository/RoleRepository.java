package com.ibm.pixogram.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.ibm.pixogram.models.Role;



public interface RoleRepository extends MongoRepository<Role, String> {
	Role findByRole(String role);
}
