package com.projects.eShopping.model;

import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable=false)
	private String securityQuestion;
	@Column(nullable=false)
	private String securityAnswer;
	@OneToMany
	private Map<String, Product> cartOrders;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Map<String, Product> listings;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(long id, String username, String password, String securityQuestion, String securityAnswer,
			Map<String, Product> cartOrders, Map<String, Product> listings) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.cartOrders = cartOrders;
		this.listings = listings;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public Map<String, Product> getCartOrders() {
		return cartOrders;
	}

	public void setCartOrders(Map<String, Product> cartOrders) {
		this.cartOrders = cartOrders;
	}

	public Map<String, Product> getListings() {
		return listings;
	}

	public void setListings(Map<String, Product> listings) {
		this.listings = listings;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", securityQuestion="
				+ securityQuestion + ", securityAnswer=" + securityAnswer + ", cartOrders=" + cartOrders + ", listings="
				+ listings + "]";
	}
	
}
