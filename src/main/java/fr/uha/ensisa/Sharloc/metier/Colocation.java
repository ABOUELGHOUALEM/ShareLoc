package fr.uha.ensisa.Sharloc.metier;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Colocation {
	@Id
	@GeneratedValue
	private int colocation_id;
	@Column(unique = true, nullable = false)
	private String name;
	@OneToMany(mappedBy="userID")
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

	public int getColocation_id() {
		return colocation_id;
	}

	public void setColocation_id(int colocation_id) {
		this.colocation_id = colocation_id;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	
}
