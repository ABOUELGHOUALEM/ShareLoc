package fr.uha.ensisa.Sharloc.metier;

import java.awt.Image;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Cascade;
@Entity
public class AchievedService implements Serializable{
	@Id
	@GeneratedValue
	private Long achieved_id;
	@ManyToOne
	@JoinColumn(name="USER_NAME")
	private User from;
	
	@OneToMany(mappedBy="userID")
	private Collection<User> to;
	private Date date;
	@Lob
	private byte[] photo;
	
	private Boolean valid;
	@OneToMany(mappedBy="service_id")
	private List<Service> lService;
	private int validation;
	
	public AchievedService() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AchievedService(User from, Boolean valid) {
		super();
		this.from = from;
		this.valid = valid;
	}
	public AchievedService(User from, Date date, Boolean valid) {
		super();
		this.from = from;
		this.date = date;
		this.valid = valid;
	}
	public AchievedService(User from, Collection<User> to, Boolean valid) {
		super();
		this.from = from;
		this.to = to;
		this.valid = valid;
	}
	public AchievedService(User from, Collection<User> to, Date date, Boolean valid) {
		super();
		this.from = from;
		this.to = to;
		this.date = date;
		this.valid = valid;
	}
	
	public AchievedService(User from, Collection<User> to, byte[] photo, Boolean valid) {
		super();
		this.from = from;
		this.to = to;
		this.photo = photo;
		this.valid = valid;
	}
	
	public AchievedService(User from, Collection<User> to, Date date, byte[] photo, Boolean valid) {
		super();
		this.from = from;
		this.to = to;
		this.date = date;
		this.photo = photo;
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
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Long getAchieved_id() {
		return achieved_id;
	}
	public void setAchieved_id(Long achieved_id) {
		this.achieved_id = achieved_id;
	}
	public int getValidation() {
		return validation;
	}
	public void setValidation(int validation) {
		this.validation = validation;
	}
	
	
}
