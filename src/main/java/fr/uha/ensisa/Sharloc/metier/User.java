package fr.uha.ensisa.Sharloc.metier;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ManyToAny;

@Entity
public class User {
	@Id
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	@ManyToOne
	@JoinColumn(name="COL_NAME")
	private Colocation colocation;
	//@ManyToOne
	//@JoinColumn(name="achieved_id")
	// AchievedService achievedService;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String email, String password, String firstname, String lastname, Colocation colocation) {
		super();
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.colocation = colocation;
	}
	public User(String email, String password, String firstname, String lastname) {
		super();
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
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
	public Colocation getColocation() {
		return colocation;
	}
	public void setColocation(Colocation colocation) {
		this.colocation = colocation;
	}
	
	
}
