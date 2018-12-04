package fr.uha.ensisa.Sharloc.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.uha.ensisa.Sharloc.dao.ColocationRepository;
import fr.uha.ensisa.Sharloc.dao.UserRepository;
import fr.uha.ensisa.Sharloc.metier.Colocation;
import fr.uha.ensisa.Sharloc.metier.User;

@Controller
@Scope("SCOPE_SESSION")
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ColocationRepository colocationRespository;
	
	String logS;
	
	@RequestMapping(value="/index")
	public String index() {
		return "Accueil";
}
	
	@RequestMapping(value="/sinscrireU",method=RequestMethod.POST)
	public String sinscrire(Model model,String lastname,String firstname,String email,@Valid String pass) {

		userRepository.save(new User(email, pass, firstname, lastname));
		return "Connexion";
	}
	
	@RequestMapping(value="/passchange",method=RequestMethod.POST)
	public String changePass(String pass, String password) {
		
		User user = userRepository.findByEmail(logS);
		if (user.getPassword().equals(password.toString()))
		{
			user.setPassword(pass);
			userRepository.save(user);
		}
		System.out.println(password);
		return "profil";
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
	@RequestMapping(value="/profil")
	public String addprofil() {
		return "profil";
}
	@RequestMapping(value="/inscription")
	public String addInsciption() {
		return "hello";
}
	
	@RequestMapping(value="/connexion")
	public String addConnexion() {
		return "Connexion";
}
	@RequestMapping(value="/seconnecter",method=RequestMethod.POST)
	public String seconnecter(Model model,String email,String pass, HttpSession session) {
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
			String firstname = a.getFirstname();
			String lastname = a.getLastname();
			String password = a.getPassword();	
			session.setAttribute("firstname", firstname);
			session.setAttribute("lastname", lastname);
			session.setAttribute("password", password);
			model.addAttribute("firstname",firstname);
			model.addAttribute("lastname",lastname);
			model.addAttribute("password",password);
			return "Home";
		}
	}
	
}
