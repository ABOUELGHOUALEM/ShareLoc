package fr.uha.ensisa.Sharloc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.uha.ensisa.Sharloc.dao.ColocationRepository;
import fr.uha.ensisa.Sharloc.dao.ServiceRepository;
import fr.uha.ensisa.Sharloc.dao.UserRepository;
import fr.uha.ensisa.Sharloc.metier.Colocation;
import fr.uha.ensisa.Sharloc.metier.Service;
import fr.uha.ensisa.Sharloc.metier.User;

@Controller
public class ServiceController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ColocationRepository colocationRespository;
	@Autowired 
	ServiceRepository serviceRepository;
	
	@RequestMapping(value="/serviceadd",method=RequestMethod.POST)
	public String ajoutService(String email,String name, String title, String description, int cost ) {
		Colocation colocation = colocationRespository.findColocationByName(name);
		User user = userRepository.findByEmail(email);
		if(user != null && name != null  && user.getColocation().getName().equals(colocation.getName()) ) {
		serviceRepository.save(new Service(colocation, user, title, description, cost));
		return "Connexion";
		}
		else
			return "Accueil";
	}
	@RequestMapping(value="/serviceupdate",method=RequestMethod.POST)
	public String updateService(String title, String description, int cost ) {
		Service service = serviceRepository.findServiceByTitle(title);
		if(service != null  ) {
			service.setDescription(description);
			service.setCost(cost);
		serviceRepository.save(service);
		return "Connexion";
		}
		else
			return "Accueil";
	}
	@RequestMapping(value="/servicedelete",method=RequestMethod.POST)
	public String deleteService(String title) {
		Service service = serviceRepository.findServiceByTitle(title);
		if(service != null  ) {
		serviceRepository.delete(service);
		return "Connexion";
		}
		else
			return "Accueil";
	}
	
	
}
