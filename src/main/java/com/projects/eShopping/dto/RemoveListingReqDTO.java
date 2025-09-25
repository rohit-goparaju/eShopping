package com.projects.eShopping.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RemoveListingReqDTO {
	
	@NotNull
	@Pattern(regexp = "^[a-z][a-z0-9]{1,9}(?:@eShopping\\.in)$", message = "username must start with an alphabet, can contain only lowercase and numbers, length of the prefix must be between 2 to 10 inclusive, must end with @eShopping.in")
	private String username;
	@NotNull
	private long productId;
	public RemoveListingReqDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RemoveListingReqDTO(
			@NotNull @Pattern(regexp = "^[a-z][a-z0-9]{1,9}(?:@eShopping\\.in)$", message = "username must start with an alphabet, can contain only lowercase and numbers, length of the prefix must be between 2 to 10 inclusive, must end with @eShopping.in") String username,
			@NotNull long productId) {
		super();
		this.username = username;
		this.productId = productId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		return "RemoveListingReqDTO [username=" + username + ", productId=" + productId + "]";
	}
	
}
