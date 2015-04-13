package com.dihaw.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ATTEMPTS")
public class UserAttempts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "ATTEMPTS")
	private int 	attempts;
	
	@Column(name = "LAST_MODIFIED")
	private Date 	lastModified;
	
	/**
     * constructor for ORM.
     */
	public UserAttempts(){
	}
	
	public UserAttempts(String username, int attempts, Date lastModified){
		this.username = username;
		this.attempts = attempts;
		this.lastModified = lastModified;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

}
