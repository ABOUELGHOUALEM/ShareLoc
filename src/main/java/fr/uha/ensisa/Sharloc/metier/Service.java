package fr.uha.ensisa.Sharloc.metier;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
 public class Service {
	@Id
	@GeneratedValue
	private int service_id;
	@ManyToOne()
	@JoinColumn(name="COLOCATION_NAME")
	private Colocation colocation;
	@ManyToOne
	@JoinColumn(name="USER_NAME")
	private User user;
	private String title;
	private String description;
	private int cost; //nombre de point associé au service
	private int vote;
	//@OneToOne(mappedBy="achieved_id", cascade= CascadeType.ALL) 
	//private AchievedService achievedService;
	
	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Service(Colocation colocation, String title, String description, int cost) {
		super();
		this.colocation = colocation;
		this.title = title;
		this.description = description;
		this.cost = cost;
	}
	public Service(Colocation colocation, User user, String title, String description, int cost) {
		super();
		this.colocation = colocation;
		this.user = user;
		this.title = title;
		this.description = description;
		this.cost = cost;
	}
	public int getService_id() {
		return service_id;
	}
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}
	public Colocation getColocation() {
		return colocation;
	}
	public void setColocation(Colocation colocation) {
		this.colocation = colocation;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	
	
}
