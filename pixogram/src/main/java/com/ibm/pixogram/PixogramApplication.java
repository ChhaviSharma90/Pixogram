package com.ibm.pixogram;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.ibm.pixogram.fileproperty.FileStorageProperties;
import com.ibm.pixogram.models.Role;
import com.ibm.pixogram.repository.RoleRepository;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class PixogramApplication {

	public static void main(String[] args) {
		SpringApplication.run(PixogramApplication.class, args);
	}
	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {

	    return args -> {

	        Role adminRole = roleRepository.findByRole("ADMIN");
	        if (adminRole == null) {
	            Role newAdminRole = new Role();
	            newAdminRole.setRole("ADMIN");
	            roleRepository.save(newAdminRole);
	        }

	        Role userRole = roleRepository.findByRole("USER");
	        if (userRole == null) {
	            Role newUserRole = new Role();
	            newUserRole.setRole("USER");
	            roleRepository.save(newUserRole);
	        }
	    };

	}
}
