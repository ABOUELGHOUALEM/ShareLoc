package fr.uha.ensisa.Sharloc.metier;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ManyToAny;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements Serializable, UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long userID;
	private String email;
	@JsonIgnore
	private String password;
	private String firstname;
	private String lastname;
	@Column(unique = true, nullable = false)
	private String username;
	
	@ManyToOne
	@JoinColumn(name="COL_NAME")
	private Colocation colocation;
	private int credits;
	
	@OneToMany(mappedBy="achieved_id")
	private List<AchievedService> lAchievedService;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "User_Role", joinColumns = {
            @JoinColumn(name = "idUsers") }, inverseJoinColumns = {
            @JoinColumn(name = "idRole") })
    private Set<Role> roles;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String email, Colocation colocation) {
		super();
		this.email = email;
		this.colocation = colocation;
	}
	public User(UserItem user) {
		this.email=user.getEmail();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.password = user.getPassword();
		this.username = user.getUsername();
	}

	public User(String email, String password, String firstname, String lastname, String username,
			Colocation colocation, int credits) {
		super();
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.colocation = colocation;
		this.credits = credits;
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
	@JsonIgnore
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

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public List<AchievedService> getlAchievedService() {
		return lAchievedService;
	}

	public void setlAchievedService(List<AchievedService> lAchievedService) {
		this.lAchievedService = lAchievedService;
	}
	@JsonIgnore
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", email=" + email + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", username=" + username + ", credits=" + credits + "]";
	}
	
}
