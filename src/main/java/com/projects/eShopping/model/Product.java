package com.projects.eShopping.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

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
	@ManyToOne(optional = false)
	private User seller;
	@ManyToOne
	private User buyer;
	@Lob
	private byte[] productImage;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(long id, String name, String description, BigDecimal price, User seller, User buyer,
			byte[] productImage) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.seller = seller;
		this.buyer = buyer;
		this.productImage = productImage;
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
	public User getSeller() {
		return seller;
	}
	public void setSeller(User seller) {
		this.seller = seller;
	}
	public User getBuyer() {
		return buyer;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	public byte[] getProductImage() {
		return productImage;
	}
	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", seller=" + seller.getUsername() + ", buyer=" + (buyer!=null?buyer.getUsername(): "");
	}
	
}
