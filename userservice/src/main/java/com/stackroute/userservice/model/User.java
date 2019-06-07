package com.stackroute.userservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@Column(length = 50)
	private String userId;
	
	private String name;
	private String email;
	private String password;
	
	private Date createdDate = new Date();
	
	public User() {
	}

	public User(String userId, String name, String email, String password, Date createdDate) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.createdDate = createdDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", createdDate=" + createdDate + "]";
	}
}
