package fr.uha.ensisa.Sharloc.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ChatColocation {
	@Id
	@GeneratedValue
	private int chat_id;
	@ManyToOne()
	@JoinColumn(name="COLOCATION_NAME")
	private Colocation colocation;
	@ManyToOne
	@JoinColumn(name="USER_NAME")
	private User user;
	private String message;
	
	public ChatColocation(Colocation colocation, User user, String message) {
		super();
		this.colocation = colocation;
		this.user = user;
		this.message = message;
	}
	
	public ChatColocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChatColocation(String message) {
		super();
		this.message = message;
	}

	public int getChat_id() {
		return chat_id;
	}
	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
