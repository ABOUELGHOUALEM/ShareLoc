package fr.uha.ensisa.Sharloc.metier;

import java.awt.Image;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Cascade;
@Entity
public class AchievedService {
	@Id
	@GeneratedValue
	private int achieved_id;
	@ManyToOne
	@JoinColumn(name="USER_NAME")
	private User from;
	
	@OneToMany(mappedBy="email")
	private Collection<User> to;
	private Date date;
	//private Image picture;
	private Boolean valid;
	//@OneToOne
	//@PrimaryKeyJoinColumn
	//private Service service;
	
	public AchievedService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AchievedService(Colocation colocation, String title, String description, int cost) {
		super();
		// TODO Auto-generated constructor stub
	}

	public AchievedService(User from, Collection<User> to, Date date, Boolean valid) {
		super();
		this.from = from;
		this.to = to;
		this.date = date;
		
		this.valid = valid;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public Collection<User> getTo() {
		return to;
	}

	public void setTo(Collection<User> to) {
		this.to = to;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	
}
