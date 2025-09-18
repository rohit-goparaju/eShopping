package com.projects.eShopping.dto;

public class AddUserResponseDTO {

	private String username;

	public AddUserResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddUserResponseDTO(String username) {
		super();
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
