package com.projects.eShopping.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserLoginRequestDTO {

	@NotNull
	@Pattern(regexp = "^[a-z][a-z0-9]{1,9}(?:@eShopping\\.in)$", message = "username must start with an alphabet, can contain only lowercase and numbers, length of the prefix must be between 2 to 10 inclusive, must end with @eShopping.in")
	private String username;
	@NotNull
	@Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*@)(?=.*\\d)[a-zA-Z][a-zA-Z0-9@]{7,}", message = "password must be atleast 8 characters long, must contain lowercase, uppercase, @ and digits")
	private String password;
	public UserLoginRequestDTO() {
		super();
	}
	public UserLoginRequestDTO(
			@NotNull @Pattern(regexp = "^[a-z][a-z0-9]{1,9}(?:@eShopping\\.in)$", message = "username must start with an alphabet, can contain only lowercase and numbers, length of the prefix must be between 2 to 10 inclusive, must end with @eShopping.in") String username,
			@NotNull @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*@)(?=.*\\d)[a-zA-Z][a-zA-Z0-9@]{7,}", message = "password must be atleast 8 characters long, must contain lowercase, uppercase, @ and digits") String password) {
		super();
		this.username = username;
		this.password = password;
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
}
