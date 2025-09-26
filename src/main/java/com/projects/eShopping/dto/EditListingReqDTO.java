package com.projects.eShopping.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class EditListingReqDTO {

	@NotNull
	private String productCode;
	@NotNull
	private String name;
	@NotNull
	private String description;
	@NotNull
	@Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "should be a currency number format.")
	private String price;
	@NotNull
	@Pattern(regexp = "^[a-z][a-z0-9]{1,9}(?:@eShopping\\.in)$", message = "username must start with an alphabet, can contain only lowercase and numbers, length of the prefix must be between 2 to 10 inclusive, must end with @eShopping.in")
	private String sellerUsername;
	public EditListingReqDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EditListingReqDTO(@NotNull String productCode, @NotNull String name, @NotNull String description,
			@NotNull @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "should be a currency number format.") String price,
			@NotNull @Pattern(regexp = "^[a-z][a-z0-9]{1,9}(?:@eShopping\\.in)$", message = "username must start with an alphabet, can contain only lowercase and numbers, length of the prefix must be between 2 to 10 inclusive, must end with @eShopping.in") String sellerUsername) {
		super();
		this.productCode = productCode;
		this.name = name;
		this.description = description;
		this.price = price;
		this.sellerUsername = sellerUsername;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSellerUsername() {
		return sellerUsername;
	}
	public void setSellerUsername(String sellerUsername) {
		this.sellerUsername = sellerUsername;
	}
	@Override
	public String toString() {
		return "EditListingReqDTO [productCode=" + productCode + ", name=" + name + ", description=" + description
				+ ", price=" + price + ", sellerUsername=" + sellerUsername + "]";
	}
}
