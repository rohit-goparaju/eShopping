package com.projects.eShopping.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class FindMyListingsReqDTO {

	@NotNull
	@Pattern(regexp = "^[a-z][a-z0-9]{1,9}(?:@eShopping\\.in)$", message = "username must start with an alphabet, can contain only lowercase and numbers, length of the prefix must be between 2 to 10 inclusive, must end with @eShopping.in")
	private String username;
	@NotNull
	private int pageNumber;
	@NotNull
	private int size;

	public FindMyListingsReqDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FindMyListingsReqDTO(
			@NotNull @Pattern(regexp = "^[a-z][a-z0-9]{1,9}(?:@eShopping\\.in)$", message = "username must start with an alphabet, can contain only lowercase and numbers, length of the prefix must be between 2 to 10 inclusive, must end with @eShopping.in") String username,
			@NotNull int pageNumber, @NotNull int size) {
		super();
		this.username = username;
		this.pageNumber = pageNumber;
		this.size = size;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "FindMyListingsReqDTO [username=" + username + ", pageNumber=" + pageNumber + ", size=" + size + "]";
	}
	
}
