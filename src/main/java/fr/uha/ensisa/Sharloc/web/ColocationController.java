package fr.uha.ensisa.Sharloc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.uha.ensisa.Sharloc.dao.ColocationRepository;
import fr.uha.ensisa.Sharloc.dao.UserRepository;
import fr.uha.ensisa.Sharloc.metier.Colocation;
import fr.uha.ensisa.Sharloc.metier.User;

@Controller
public class ColocationController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ColocationRepository colocationRespository;
	
	
	@RequestMapping(value="/colocationadd",method=RequestMethod.POST)
	public String ajoutCol(String email,String name) {
		Colocation colocation = new Colocation(name);
		User user = userRepository.findByEmail(email);
		if(user != null && name != null ) {
		colocationRespository.save(colocation);
		userRepository.save(new User(email,colocation));
		return "Connexion";
		}
		else
			return "Accueil";
	}
	
	@RequestMapping(value="/colocationdelete",method=RequestMethod.POST)
	public String deleteCol(String email,String name) {
		
		User user = userRepository.findByEmail(email);
		Colocation colocation = colocationRespository.findColocationByName(name);
		if(user != null && colocation != null) {
			user.setColocation(null);
			userRepository.save(user);
			colocationRespository.delete(colocation);
		return "Connexion";
		}
		else
			return "Accueil";
	}

	
}
