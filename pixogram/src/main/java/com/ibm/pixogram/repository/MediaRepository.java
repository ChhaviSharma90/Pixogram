package com.ibm.pixogram.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.pixogram.models.UploadFileResponse;
import com.ibm.pixogram.models.User;
import com.ibm.pixogram.models.UserProfilePictures;


public interface MediaRepository extends MongoRepository<UploadFileResponse, String> {
	List<UploadFileResponse> findAllByUsername(String username);


}
