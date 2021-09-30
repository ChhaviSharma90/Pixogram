package com.ibm.pixogram.controller;
import static org.springframework.http.ResponseEntity.ok;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ibm.pixogram.configs.JwtTokenProvider;
import com.ibm.pixogram.models.Followers;
import com.ibm.pixogram.models.FollowersRequest;
import com.ibm.pixogram.models.UploadFileResponse;
import com.ibm.pixogram.models.User;
import com.ibm.pixogram.models.UserProfilePictures;
import com.ibm.pixogram.repository.UserRepository;

import com.ibm.pixogram.services.CustomUserDetailsService;
import com.ibm.pixogram.services.FileStorageService;
import com.ibm.pixogram.services.MediaService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
    private FileStorageService fileStorageService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private MediaService mediaservice;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    private MongoTemplate mt;
    @Autowired
    UserRepository users;

    @Autowired
    private CustomUserDetailsService userService;

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthBody data) {    	
        try {
            String username = data.getUsername();
        //    System.out.println(username);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.users.findByEmail(username).getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
    //	System.out.println(user);
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + user.getEmail() + " already exists");
        }
        userService.saveUser(user);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        return ok(model);
    }
    @PostMapping("/uploadProfilePicture")
    public UserProfilePictures uploadFile(@RequestParam("mediafile") MultipartFile file,@RequestParam("username") String username,@RequestParam("createddate") String createdDate,@RequestParam("createdtime") String createdTime) {
    //	System.out.print(file);
        String fileName = fileStorageService.storeFile(file);
        System.out.print(fileName);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/downloadFile/")	
                .path(fileName)
                .toUriString();
        UserProfilePictures profilepictures=new UserProfilePictures(fileName, fileDownloadUri,
                file.getContentType(), file.getSize(),username,createdDate,createdTime);
        
        mediaservice.saveProfilePicture(profilepictures);
        return  profilepictures;
    }
    @PutMapping("/followUser")
    public void followUser(@RequestBody FollowersRequest users) {
     
//       
//        userService.updateUser(users.getUsername(),users.getFollowerUsername(),users.getEmail());
//        Map<Object, Object> model = new HashMap<>();
//        model.put("message", "User updated successfully");
//        ok(model);
    	

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is("60f69e901ae5750f1e0eefa4"));
		User e=mt.findOne(query,User.class);
		Followers follow=new Followers();
		follow.setUsername(users.getFollowerUsername());
		follow.setEmail(users.getEmail());
		e.setFollowers(new HashSet<>(Arrays.asList(follow)));
    }
}

