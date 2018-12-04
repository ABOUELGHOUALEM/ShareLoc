package fr.uha.ensisa.Sharloc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.uha.ensisa.Sharloc.dao.ColocationRepository;
import fr.uha.ensisa.Sharloc.dao.UserRepository;
import fr.uha.ensisa.Sharloc.metier.Colocation;
import fr.uha.ensisa.Sharloc.metier.User;

@SpringBootApplication
public class ShareLocApplication implements CommandLineRunner{
	@Autowired
	UserRepository userRepository;
	@Autowired
	ColocationRepository colocationRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ShareLocApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Colocation col1 = colocationRepository.save(new Colocation("RMD"));
		
		//User user1 = userRepository.save(new User("yassine@gmail.com", "yassine", "yassine", "ab",col1));
		
		
	}
}
