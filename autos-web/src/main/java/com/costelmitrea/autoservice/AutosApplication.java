package com.costelmitrea.autoservice;

import com.costelmitrea.autoservice.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class AutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutosApplication.class, args);
	}

}
