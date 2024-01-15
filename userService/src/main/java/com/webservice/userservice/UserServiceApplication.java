package com.webservice.userservice;

import com.webservice.userservice.entities.Visitor;
import com.webservice.userservice.repositories.VisitorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.UUID;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(VisitorRepository visitorRepository, RepositoryRestConfiguration restConfiguration){
		restConfiguration.exposeIdsFor(Visitor.class);
		return args -> {
		};
	}
}
