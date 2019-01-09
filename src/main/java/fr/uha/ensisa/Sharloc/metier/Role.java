package fr.uha.ensisa.Sharloc.metier;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
@Entity
public class Role implements GrantedAuthority, Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRole;
	private String name;
	private String description;
	

	public long getId() {
		return idRole;
	}

	public void setId(long id) {
		this.idRole = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getAuthority() {
		return name;
	}
}
