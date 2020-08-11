package com.ecommerce.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	private String productName;
	private Double productPrice;
	private Double productOfferPrice;
	@Lob
	private String productDescription;
	private String productColor;

	@ManyToOne()
	@JoinColumn(name = "category_id", nullable = false)
	@JsonManagedReference
	private SubCategory subCategory;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<ProductImage> productImage;

	public Product() {

	}

	public Product(String productName, Double productPrice, Double productOfferPrice, String productDescription,
			String productColor, SubCategory subCategory, List<ProductImage> productImage) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.productOfferPrice = productOfferPrice;
		this.productDescription = productDescription;
		this.productColor = productColor;
		this.subCategory = subCategory;
		this.productImage = productImage;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Double getProductOfferPrice() {
		return productOfferPrice;
	}

	public void setProductOfferPrice(Double productOfferPrice) {
		this.productOfferPrice = productOfferPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public List<ProductImage> getProductImage() {
		return productImage;
	}

	public void setProductImage(List<ProductImage> productImage) {
		this.productImage = productImage;
	}

}
