package com.projects.eShopping.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String description;
	@Column(nullable=false)
	private BigDecimal price;
	@Column(nullable = false)
	private String sellerUsername;
	@Column
	private String buyerUsername;
	@Lob
	@Column(nullable=false, columnDefinition = "LONGBLOB")
	private byte[] productImage;
	@Column(nullable = false)
	private String productImageType;
	@Column(nullable=false)
	private String productImageFileName;
	@Column(nullable=false, unique = true, updatable = false)
	private String productCode;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Product(long id, String name, String description, BigDecimal price, String sellerUsername,
			String buyerUsername, byte[] productImage, String productImageType, String productImageFileName,
			String productCode) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.sellerUsername = sellerUsername;
		this.buyerUsername = buyerUsername;
		this.productImage = productImage;
		this.productImageType = productImageType;
		this.productImageFileName = productImageFileName;
		this.productCode = productCode;
	}


	@PrePersist
	public void generateProductCode() {
		this.productCode = UUID.randomUUID().toString();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public String getSellerUsername() {
		return sellerUsername;
	}


	public void setSellerUsername(String sellerUsername) {
		this.sellerUsername = sellerUsername;
	}


	public String getBuyerUsername() {
		return buyerUsername;
	}


	public void setBuyerUsername(String buyerUsername) {
		this.buyerUsername = buyerUsername;
	}


	public byte[] getProductImage() {
		return productImage;
	}


	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}


	public String getProductImageType() {
		return productImageType;
	}


	public void setProductImageType(String productImageType) {
		this.productImageType = productImageType;
	}


	public String getProductImageFileName() {
		return productImageFileName;
	}


	public void setProductImageFileName(String productImageFileName) {
		this.productImageFileName = productImageFileName;
	}


	public String getProductCode() {
		return productCode;
	}


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
}
