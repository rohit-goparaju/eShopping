package com.projects.eShopping.dto;

public class UserLoginResponseDTO {

	private String username;
	private String jwt;
	public UserLoginResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserLoginResponseDTO(String username, String jwt) {
		super();
		this.username = username;
		this.jwt = jwt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}	
}
