package fr.uha.ensisa.Sharloc.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.uha.ensisa.Sharloc.dao.ChatColocationRepository;
import fr.uha.ensisa.Sharloc.dao.ColocationRepository;
import fr.uha.ensisa.Sharloc.dao.UserRepository;
import fr.uha.ensisa.Sharloc.metier.ChatColocation;
import fr.uha.ensisa.Sharloc.metier.Colocation;
import fr.uha.ensisa.Sharloc.metier.User;

@Controller
@RequestMapping("/colocation")
public class ColocationController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ColocationRepository colocationRespository;
	@Autowired
	ChatColocationRepository chatColocationRepository;
	
	
	@RequestMapping(value="/colocationadd",method=RequestMethod.POST)
	public ResponseEntity<?>  ajoutCol(@RequestBody Colocation colocation) {
		
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Colocation colocation2 = colocationRespository.findColocationByName(colocation.getName());
		
		try {
			if(colocation2 != null) {
			colocation2 = colocationRespository.save(colocation2);
			user.setColocation(colocation2);
			userRepository.save(user);
			return ResponseEntity.ok(colocation2);
			}
			else
			{
				colocation = colocationRespository.save(colocation);
				user.setColocation(colocation);
				userRepository.save(user);
				return ResponseEntity.ok(colocation);	
			}
			
		}
		catch (Exception e) {
			return ResponseEntity.ok(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value="/colocationdelete",method=RequestMethod.POST)
	public ResponseEntity<?>  deleteCol(@RequestBody Colocation colocatione) {
		
		User user2 =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Colocation colocation = colocationRespository.findColocationByName(colocatione.getName());
		List<User> lUsers = userRepository.getUsersPerColocation(colocation.getName());
		User user = userRepository.findByUsername(user2.getUsername());
		if(user.getColocation().getColocation_id() == colocation.getColocation_id()) {
			for(User u:lUsers) {
				u.setColocation(null);
				userRepository.save(user);	
			}
			colocationRespository.delete(colocation);
			return ResponseEntity.ok(user);}
		return ResponseEntity.ok(HttpStatus.NOT_FOUND);
		
	}
	
	@RequestMapping(value="/colocationchat",method=RequestMethod.POST)
	public ResponseEntity<?>  chatCol(@RequestBody ChatColocation chatColocation) {
		
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ChatColocation chatColocation2 = chatColocationRepository.save(new ChatColocation(user.getColocation(), user, chatColocation.getMessage()));
			return ResponseEntity.ok(chatColocation2);
		
	}
	
	@RequestMapping(value="/getchat",method=RequestMethod.GET)
	public ResponseEntity<?>  getchat() {
		List<ChatColocation> lChat = new ArrayList<>();
		User user2 =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUsername(user2.getUsername());
		 lChat = chatColocationRepository.getMessagePerColocation(user.getColocation().getName());
		 //return ResponseEntity.ok(lChat);
		 	try{
				return ResponseEntity.ok(lChat);
			} catch (Exception e) {
				return ResponseEntity.ok(HttpStatus.NOT_FOUND);
			}
		
		
	}
	
}
