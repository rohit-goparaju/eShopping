package com.projects.eShopping.dto;

public class ResetPasswordResDTO {
	private String username;
	private String jwt;
	public ResetPasswordResDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResetPasswordResDTO(String username, String jwt) {
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
	@Override
	public String toString() {
		return "ResetPasswordResDTO [username=" + username + ", jwt=" + jwt + "]";
	}
}
