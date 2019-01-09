package fr.uha.ensisa.Sharloc.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.uha.ensisa.Sharloc.dao.AchievedServiceRepository;
import fr.uha.ensisa.Sharloc.dao.ChatColocationRepository;
import fr.uha.ensisa.Sharloc.dao.ColocationRepository;
import fr.uha.ensisa.Sharloc.dao.ServiceRepository;
import fr.uha.ensisa.Sharloc.dao.UserRepository;
import fr.uha.ensisa.Sharloc.metier.AchievedService;
import fr.uha.ensisa.Sharloc.metier.Colocation;
import fr.uha.ensisa.Sharloc.metier.Service;
import fr.uha.ensisa.Sharloc.metier.User;

@Controller
@CrossOrigin("*")
@RequestMapping("/AchievedService")
public class AchievedServiceController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ColocationRepository colocationRespository;
	@Autowired
	ChatColocationRepository chatColocationRepository;
	@Autowired
	ServiceRepository serviceRepository;
	@Autowired
	AchievedServiceRepository achievedServiceRepository;
	
	List<String> votedEmail = new ArrayList<>();
	
	@RequestMapping(value="/achieveserviceadd{id}",method=RequestMethod.POST)
	public ResponseEntity<?>  achieveService(@PathVariable long id, 
			@RequestParam(value="file")MultipartFile file) {
		
		AchievedService achievedService = new AchievedService();
		User user2 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUsername(user2.getUsername());
		Service service = serviceRepository.findServiceById(id);
		Colocation colocation = colocationRespository.findColocationByName(user.getColocation().getName());
		List<User> lUsers = userRepository.getUsersPerColocation(colocation.getName());
		List<User> lUsersN = new ArrayList<>();
		
		Boolean validUser=false;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		for(User u:lUsers) {
			if(u.getUsername().equals(user.getUsername())) {
				validUser=true;
			}
			else
			{
				lUsersN.add(u);
			}
		}
		if(service != null && validUser == true && file.getSize() != 0 ) {
		System.err.println(service);
		try {
			Long millis = System.currentTimeMillis();
			//Date date = new Date(millis);
			//date = formatter.parse(date.toString());
			if(service.getAchievedService()==null) {
				if(service.getVote()>40) {
					//achievedService.setDate(date);
					achievedService.setFrom(user);
					achievedService.setPhoto(file.getBytes());
					achievedService.setValid(false);
					achievedService.setTo(lUsersN);
			
			achievedService = achievedServiceRepository.save(achievedService);
			service.setAchievedService(achievedService);
			serviceRepository.save(service);
			return ResponseEntity.ok(achievedService);
				}
				else {System.err.println("le service n'a pas était voté positivement par tous les membres.");}
			}
			else {
				System.out.println("le service :"+service.getTitle()+" a était deja fait par :");}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(achievedService);
		}
		
			return ResponseEntity.ok(HttpStatus.FORBIDDEN);
	}
	
	@RequestMapping(value="/validationserviceAchieved",method=RequestMethod.POST)
	public ResponseEntity<?>  validationAchieveService(@RequestBody Service service2) {
		
		User user2 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Service service = serviceRepository.findServiceByTitle(service2.getTitle());
		User user = userRepository.findByUsername(user2.getUsername());
		List<User> lUsers = userRepository.getUsersPerColocation(service.getColocation().getName());
			AchievedService achievedService= achievedServiceRepository.findAchievedServiceById(service.getAchievedService().getAchieved_id());
		boolean validUser=false;
		
		for(User u:lUsers) {
			if(u.getUsername().equals(user.getUsername()) && votedEmail.contains(user.getUsername())==false) {
				validUser=true;
			}
		}
		if(service != null && validUser == true && service.getAchievedService() !=null && 
				service.getAchievedService().getValid()==false) {
			
			int usersNumber = (userRepository.countUserPerColocation(service.getColocation().getName()))-1;
			int userPeurcentage =(int) 100/usersNumber;
			
			if(service2.getVoter().equals("validé") && !user.getEmail().equals(service.getAchievedService().getFrom().getEmail()) ) {
				achievedService.setValidation(achievedService.getValidation()+userPeurcentage);
				achievedServiceRepository.save(achievedService);
				votedEmail.add(user.getUsername());
				if(achievedService.getValidation()>50) {
					achievedService.setValid(true);
					User userAchieved = achievedService.getFrom();
					userAchieved.setCredits(userAchieved.getCredits()+service.getCost());
					userRepository.save(userAchieved);
					achievedServiceRepository.save(achievedService);
				}else {System.err.println("sa validation n'est pas confirmé par les autres membres");}
				return ResponseEntity.ok(achievedService);
			}
			else {System.err.println("vous avez choisi de ne pas valider le service ou vous n'avez pas le droit de valider");}
		
			return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.ok(HttpStatus.FORBIDDEN);
	}
	
	
}
