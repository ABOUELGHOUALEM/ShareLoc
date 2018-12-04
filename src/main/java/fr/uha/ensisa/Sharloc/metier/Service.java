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
	private String title;
	private String description;
	private int cost; //nombre de point associé au service
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
	
	
}
