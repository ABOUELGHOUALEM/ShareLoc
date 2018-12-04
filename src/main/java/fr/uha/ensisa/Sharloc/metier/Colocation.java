package fr.uha.ensisa.Sharloc.metier;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Colocation {
	@Id
	@GeneratedValue
	private int colocation_id;
	private String name;
	@OneToMany(mappedBy="email")
	private Collection<User> users;
	public Colocation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Colocation(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
