package fr.uha.ensisa.Sharloc.metier;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserItem implements Serializable{

	private String email;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	
	public UserItem(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public UserItem(String email, String password, String firstname, String lastname) {
		super();
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public UserItem(String email, String password, String firstname, String lastname, String username) {
		super();
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
	}
	public UserItem() {
		super();
		// TODO Auto-generated constructor stub
	}
public UserItem(User user) {
		this.email=user.getEmail();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
