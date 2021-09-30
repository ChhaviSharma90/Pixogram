package com.ibm.pixogram.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.pixogram.models.UploadFileResponse;
import com.ibm.pixogram.models.User;
import com.ibm.pixogram.models.UserProfilePictures;
import com.ibm.pixogram.repository.MediaRepository;
import com.ibm.pixogram.repository.ProfilePictureRepo;

@Service
public class MediaService {

    @Autowired
    private  MediaRepository mediaRepo;
    @Autowired
    private  ProfilePictureRepo picRepo;
    
    public void saveFileDetails(UploadFileResponse filedetails)
    {
          mediaRepo.save(filedetails);
    }
    public void saveProfilePicture(UserProfilePictures filedetails)
    {
          picRepo.save(filedetails);
    }
    public List<UploadFileResponse> findAllFiles() {
        return mediaRepo.findAll();
        
    }
    public List<UserProfilePictures> findallprofilepictures() {
        return picRepo.findAll();
        
    }
}
