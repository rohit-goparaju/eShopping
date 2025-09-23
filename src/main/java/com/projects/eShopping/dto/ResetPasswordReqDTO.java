package com.projects.eShopping.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ResetPasswordReqDTO {
	
	@NotNull
	@Pattern(regexp = "^[a-z][a-z0-9]{1,9}(?:@eShopping\\.in)$", message = "username must start with an alphabet, can contain only lowercase and numbers, length of the prefix must be between 2 to 10 inclusive, must end with @eShopping.in")
	private String username;
	@Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*@)(?=.*\\d)[a-zA-Z][a-zA-Z0-9@]{7,}", message = "password must be atleast 8 characters long, must contain lowercase, uppercase, @ and digits")
	private String newPassword;
	public ResetPasswordReqDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResetPasswordReqDTO(
			@NotNull @Pattern(regexp = "^[a-z][a-z0-9]{1,9}(?:@eShopping\\.in)$", message = "username must start with an alphabet, can contain only lowercase and numbers, length of the prefix must be between 2 to 10 inclusive, must end with @eShopping.in") String username,
			@Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*@)(?=.*\\d)[a-zA-Z][a-zA-Z0-9@]{7,}", message = "password must be atleast 8 characters long, must contain lowercase, uppercase, @ and digits") String newPassword) {
		super();
		this.username = username;
		this.newPassword = newPassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "ResetPasswordReqDTO [username=" + username + ", newPassword=" + newPassword + "]";
	}
}
