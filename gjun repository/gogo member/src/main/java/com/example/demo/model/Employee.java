package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="employees")
public class Employee {
	@Id	
    Integer id;
//	@Column(name="username")
    String username;
//	@Column(name="password")
    String password;
    String email;
    
    public Employee() {}
	public Employee(Integer id, String username, String password, String email) {	
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public Integer getId() {
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
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	
	
    
}
