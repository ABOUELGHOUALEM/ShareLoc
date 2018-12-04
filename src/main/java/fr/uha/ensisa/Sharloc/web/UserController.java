package fr.uha.ensisa.Sharloc.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ColocationRepository colocationRespository;
	
	String logS;
	
	@RequestMapping(value="/index")
	public String index() {
		return "hello";
}
	
	@RequestMapping(value="/sinscrireU",method=RequestMethod.POST)
	public String sinscrire(Model model,String lastname,String firstname,String email,@Valid String pass) {

		userRepository.save(new User(email, pass, firstname, lastname));

		return "Connexion";
	}
	//colocation
	@RequestMapping(value="/colocationadd",method=RequestMethod.POST)
	public String ajoutCol(Model model,String email,String name) {
		Colocation colocation = new Colocation(name);
		colocationRespository.save(colocation);
		userRepository.save(new User(logS,colocation));
		

		return "Connexion";
	}
	@RequestMapping(value="/colocation")
	public String addColocation() {
		return "Colocation";
}
	
	@RequestMapping(value="/seconnecter",method=RequestMethod.POST)
	public String seconnecter(Model model,String email,String pass,HttpSession  session) {
		User a=null;
		a=userRepository.login(email, pass);
		if(a==null){
			return "Connexion";
		}else{
			session.setAttribute("email", email);
			String ap=(String) session.getAttribute("email");
			logS=ap;
			System.out.println(logS);
			model.addAttribute("email",email);
			return "Home";
		}
	}
	
	/*@RequestMapping(value="/profilapp")
	public String profilapp(Model model,HttpSession  session) {
		
		System.out.println(logS);
		return "profile";}

	@RequestMapping(value="/profile")
	public String profil(Model model) {
	User a;
	a=userRepository.findByEmail(logS);
		model.addAttribute("email");
		model.addAttribute("profile", a);
		return "profile";
	}*/
}
