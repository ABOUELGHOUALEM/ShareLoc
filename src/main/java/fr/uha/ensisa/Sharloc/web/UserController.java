package fr.uha.ensisa.Sharloc.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import fr.uha.ensisa.Sharloc.dao.ColocationRepository;
import fr.uha.ensisa.Sharloc.dao.UserRepository;
import fr.uha.ensisa.Sharloc.metier.Colocation;
import fr.uha.ensisa.Sharloc.metier.User;
import fr.uha.ensisa.Sharloc.metier.UserItem;
import fr.uha.ensisa.Sharloc.sec.AuthenticationToken;
import fr.uha.ensisa.Sharloc.sec.TokenProvider;

@RestController
@CrossOrigin("*")
@RequestMapping("/checking")
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ColocationRepository colocationRespository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
    private TokenProvider jwtTokenUtil;
	
	String logS;
	

	
	@RequestMapping(value="/sinscrireU",method=RequestMethod.POST)
	public ResponseEntity<?> sinscrire(@RequestBody UserItem userItem) {
		User user = new User(userItem);
		try {
			user.setPassword(encoder.encode(user.getPassword()));
			userRepository.saveAndFlush(user);
			return ResponseEntity.ok(user);
			}
		catch (Exception e) { return new ResponseEntity<>(HttpStatus.CONFLICT);}
		}
	
	@RequestMapping(value="/seconnecter",method=RequestMethod.POST)
	public ResponseEntity<?> seconnecter(@RequestBody UserItem userItem) {
		
			final Authentication authentication;
			try {
				authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(userItem.getUsername(), userItem.getPassword()));
				System.err.println("generate token");
				SecurityContextHolder.getContext().setAuthentication(authentication);
				final String token = jwtTokenUtil.generateToken(authentication);
				return ResponseEntity.ok(new AuthenticationToken(token));
				
			}catch (Exception e) {
				System.err.println("bad credentiel");
			}
			
			return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="/unsubscribe",method=RequestMethod.POST)
	public ResponseEntity<?>  unsubscribe(@RequestBody Colocation colocatione) {
		
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Colocation colocation = colocationRespository.findColocationByName(colocatione.getName());
		if(user.getColocation().getColocation_id() == colocation.getColocation_id()) {
			user.setColocation(null);
			userRepository.save(user);
			return ResponseEntity.ok(user);}
		return ResponseEntity.ok(HttpStatus.NOT_FOUND);
		
	}
	
	@RequestMapping(value="/passchange",method=RequestMethod.POST)
	public ResponseEntity<?> changePass(@RequestBody UserItem userItem) {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u = userRepository.findByUsername(user.getUsername());
		user.setPassword(encoder.encode(userItem.getPassword()));
		user = userRepository.save(user);
		return ResponseEntity.ok(user);
	}

	
	
}
