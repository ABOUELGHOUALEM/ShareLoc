package fr.uha.ensisa.Sharloc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.uha.ensisa.Sharloc.dao.ColocationRepository;
import fr.uha.ensisa.Sharloc.dao.ServiceRepository;
import fr.uha.ensisa.Sharloc.dao.UserRepository;
import fr.uha.ensisa.Sharloc.metier.Colocation;
import fr.uha.ensisa.Sharloc.metier.Service;
import fr.uha.ensisa.Sharloc.metier.User;

@Controller
@CrossOrigin("*")
@RequestMapping("/Service")
public class ServiceController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ColocationRepository colocationRespository;
	@Autowired 
	ServiceRepository serviceRepository;
	
	@RequestMapping(value="/serviceadd",method=RequestMethod.POST)
	public ResponseEntity<?> ajoutService(@RequestBody Service service) {
		Service service2 = serviceRepository.findServiceByTitle(service.getTitle());
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(service2 == null) {
			service.setColocation(user.getColocation());
			service.setUser(user);
			service = serviceRepository.saveAndFlush(service);
			return ResponseEntity.ok(service);
		}
		else 
		{
			return ResponseEntity.ok(HttpStatus.CONFLICT);
		}
		
	}
	@RequestMapping(value="/serviceupdate",method=RequestMethod.POST)
	public ResponseEntity<?>  updateService(@RequestBody Service service) {
		Service service2 = serviceRepository.findServiceByTitle(service.getTitle());
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(service2.getUser().getUserID() == user.getUserID()  ) {
			service2.setDescription(service.getDescription());
			service2.setCost(service.getCost());
		serviceRepository.save(service);
		return ResponseEntity.ok(service2);
		}
		else
			return ResponseEntity.ok(service2);
	}
	
	@RequestMapping(value="/servicedelete",method=RequestMethod.POST)
	public ResponseEntity<?> deleteService(@RequestBody Service service) {
		Service service2 = serviceRepository.findServiceByTitle(service.getTitle());
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(service2.getUser().getUserID() == user.getUserID()  ) {
		serviceRepository.delete(service);
		return ResponseEntity.ok(service2);
		}
		return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@RequestMapping(value="/servicevote",method=RequestMethod.POST)
	public ResponseEntity<?> voteService(@RequestBody Service service) {
		Service service2 = serviceRepository.findServiceByTitle(service.getTitle());
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user2 = userRepository.findByUsername(user.getUsername());
		Colocation colocation = colocationRespository.findColocationByName(service2.getColocation().getName());
		
		if(service2 != null && user2.getColocation().getName().equals(colocation.getName()) && 
				!user2.getUsername().equals(service2.getUser().getUsername()) ) {
			
			int usersNumber = (userRepository.countUserPerColocation(colocation.getName()))-1;
			int userPeurcentage =(int) 100/usersNumber;
			
			if(service.getVoter().equals("ok") ) {
				service2.setVote(service2.getVote()+userPeurcentage);
				service2 = serviceRepository.save(service2);
			}
		
			return ResponseEntity.ok(service2);
		}
		return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
	}
	
	
}
