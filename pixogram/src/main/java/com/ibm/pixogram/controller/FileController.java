package com.ibm.pixogram.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ibm.pixogram.models.UploadFileResponse;
import com.ibm.pixogram.models.User;
import com.ibm.pixogram.models.UserProfilePictures;
import com.ibm.pixogram.repository.MediaRepository;
import com.ibm.pixogram.repository.ProfilePictureRepo;
import com.ibm.pixogram.services.CustomUserDetailsService;
import com.ibm.pixogram.services.FileStorageService;
import com.ibm.pixogram.services.MediaService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/file")
	public class FileController {

	  //  private static final Logger logger = LoggerFactory.getLogger(FileController.class);
        
	    @Autowired
	    private FileStorageService fileStorageService;
	    
	    @Autowired
	    private MediaService mediaservice;
	    
	    @Autowired
	    private CustomUserDetailsService userService;
	    
	    @Autowired
	    private  MediaRepository mediaRepo;
	    
	    @Autowired
	    private  ProfilePictureRepo picRepo;
	    
	    @PostMapping("/uploadFile")
	    public UploadFileResponse uploadFile(@RequestParam("mediafile") MultipartFile file,@RequestParam("desc") String description,@RequestParam("title") String title,@RequestParam("tags") String tags,@RequestParam("username") String username,@RequestParam("createddate") String createdDate,@RequestParam("createdtime") String createdTime) {
	    	System.out.print(file);
	        String fileName = fileStorageService.storeFile(file);
            System.out.print(fileName);
	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/file/downloadFile/")	
	                .path(fileName)
	                .toUriString();
	        UploadFileResponse uploadfileresponse=new UploadFileResponse(fileName, fileDownloadUri,
	                file.getContentType(), file.getSize(),description,title,tags,username,createdDate,createdTime);
	        
	        mediaservice.saveFileDetails(uploadfileresponse);
	        return  uploadfileresponse;
	    }

	    @PostMapping("/uploadMultipleFiles")
	    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@RequestParam("desc") String description,@RequestParam("title") String title,@RequestParam("tags") String tags,@RequestParam("username") String username,@RequestParam("createddate") String createdDate,@RequestParam("createdtime") String createdTime 	) {
	        return Arrays.asList(files)
	                .stream()
	                .map(file -> uploadFile(file,description,title,tags,username,createdDate,createdTime))
	                .collect(Collectors.toList());
	    }

	    @GetMapping("/downloadFile/{fileName:.+}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
	        // Load file as Resource
	        Resource resource = fileStorageService.loadFileAsResource(fileName);

	        // Try to determine file's content type
	        String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	            //logger.info("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    
}
	    @PostMapping("/findallfilesbyusername")
	    public List<UploadFileResponse> findAllFileByUsername(@RequestParam("username") String username) {
	    
	    	List<UploadFileResponse> filesList = new ArrayList<UploadFileResponse>();
	    	mediaRepo.findAllByUsername(username).forEach(filesList::add);
	    	return filesList;
	    }
	    @PostMapping("/findprofilepicturebyusername")
	    public List<UserProfilePictures> findProfilepictureByUsername(@RequestParam("username") String username) {
	    
	    	List<UserProfilePictures> filesList = new ArrayList<UserProfilePictures>();
	    	picRepo.findAllByUsername(username).forEach(filesList::add);
	    	return filesList;
	    }
	    @GetMapping("/findallfiles")
	    public List<UploadFileResponse> findAllfiles() {
	    
	    	List<UploadFileResponse> allfilesList = new ArrayList<UploadFileResponse>();
	    	mediaservice.findAllFiles().forEach(allfilesList::add);
	    	return allfilesList;
	    }
	    @GetMapping("/findallprofilepictures")
	    public List<UserProfilePictures> findallprofilepictures() {
	    
	    	List<UserProfilePictures> allfilesList = new ArrayList<UserProfilePictures>();
	    	mediaservice.findallprofilepictures().forEach(allfilesList::add);
	    	return allfilesList;
	    }
	    @GetMapping("/allpixogramusers")
	    public List<User> findAllusers() {
	    
	    	List<User> allusers = new ArrayList<User>();
	    	userService.findAllusers().forEach(allusers::add);
	    	return allusers;
	    }
} 
