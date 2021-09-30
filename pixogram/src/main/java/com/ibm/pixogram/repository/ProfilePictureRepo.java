package com.ibm.pixogram.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.pixogram.models.UserProfilePictures;

public interface ProfilePictureRepo extends MongoRepository<UserProfilePictures, String> {
	List<UserProfilePictures> findAllByUsername(String username);
}
