package com.miw.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	
	@Id @GeneratedValue
	private int id;
	private String username;
	private String dni;
	private String name;
	private String password;
	private Boolean admin;
	

	

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(String username,String dni,String name, String password, Boolean admin) {
		super();
		this.username = username;
		this.dni = dni;
		this.name = name;
		this.password = password;
		this.admin = admin;
	}
	


	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getAdmin() {
		return admin;
	}


	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", admin=" + admin+ "]";
	}
}
