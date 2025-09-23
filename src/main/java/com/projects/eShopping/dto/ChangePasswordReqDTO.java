package com.projects.eShopping.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ChangePasswordReqDTO {

	@NotNull
	@Pattern(regexp = "^[a-z][a-z0-9]{1,9}(?:@eShopping\\.in)$", message = "username must start with an alphabet, can contain only lowercase and numbers, length of the prefix must be between 2 to 10 inclusive, must end with @eShopping.in")
	private String username;
	@NotNull
	@Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*@)(?=.*\\d)[a-zA-Z][a-zA-Z0-9@]{7,}", message = "password must be atleast 8 characters long, must contain lowercase, uppercase, @ and digits")
	private String oldPassword;
	@NotNull
	@Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*@)(?=.*\\d)[a-zA-Z][a-zA-Z0-9@]{7,}", message = "password must be atleast 8 characters long, must contain lowercase, uppercase, @ and digits")
	private String newPassword;
	public ChangePasswordReqDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChangePasswordReqDTO(
			@NotNull @Pattern(regexp = "^[a-z][a-z0-9]{1,9}(?:@eShopping\\.in)$", message = "username must start with an alphabet, can contain only lowercase and numbers, length of the prefix must be between 2 to 10 inclusive, must end with @eShopping.in") String username,
			@NotNull @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*@)(?=.*\\d)[a-zA-Z][a-zA-Z0-9@]{7,}", message = "password must be atleast 8 characters long, must contain lowercase, uppercase, @ and digits") String oldPassword,
			@NotNull @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*@)(?=.*\\d)[a-zA-Z][a-zA-Z0-9@]{7,}", message = "password must be atleast 8 characters long, must contain lowercase, uppercase, @ and digits") String newPassword) {
		super();
		this.username = username;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "ChangePasswordReqDTO [username=" + username + ", oldPassword=" + oldPassword + ", newPassword="
				+ newPassword + "]";
	}
}
