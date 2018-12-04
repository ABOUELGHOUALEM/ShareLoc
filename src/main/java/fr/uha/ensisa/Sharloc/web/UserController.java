package fr.uha.ensisa.Sharloc.web;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.uha.ensisa.Sharloc.dao.UserRepository;
import fr.uha.ensisa.Sharloc.metier.User;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/index")
	public String index() {
		return "hello";
}
	
	@RequestMapping(value="/sinscrireU",method=RequestMethod.POST)
	public String sinscrire(Model model,String lastname,String firstname,String email,@Valid String pass) {

		userRepository.save(new User(email, pass, firstname, lastname));

		return "hello";
	}
}
