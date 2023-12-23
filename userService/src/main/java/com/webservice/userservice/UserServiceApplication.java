package com.webservice.userservice;

import com.webservice.userservice.entities.Visitor;
import com.webservice.userservice.repositories.VisitorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(VisitorRepository visitorRepository, RepositoryRestConfiguration restConfiguration){
		restConfiguration.exposeIdsFor(Visitor.class);
		return args -> {
			visitorRepository.save(new Visitor(null,"Sayagh Ilias","sayaghilias@gmail.com","0662100536","Al Azhar", "123","user"));
			visitorRepository.save(new Visitor(null,"Echchihab Younes","younes863@hotmail.fr","0762100537","Maarif", "456","admin"));
			visitorRepository.save(new Visitor(null,"admin","admin@hotmail.fr","0762100537","Maarif", "admin","admin"));
			visitorRepository.save(new Visitor(null,"admin","admin@hotmail.fr","0762100537","Maarif", "admin","admin"));
			visitorRepository.save(new Visitor(null,"admin","admin@hotmail.fr","0762100537","Maarif", "admin","admin"));
			visitorRepository.save(new Visitor(null,"Lamsouber Houssam","hox@gmail.com","0662100537","Roche Noir", "789","user"));
			visitorRepository.save(new Visitor(null,"El Aitouni Mohamed","younes863@hotmail.fr","0762100537","Maarif", "456","user"));
		};
	}
}
