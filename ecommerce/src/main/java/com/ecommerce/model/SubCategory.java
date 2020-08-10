package com.ecommerce.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "sub_category")
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long subCategoryId;

	private Boolean isActivate;

	private String subCategoryName;

	@ManyToOne()
	@JoinColumn(name = "category_id", nullable = false)
	@JsonManagedReference
	private Category category;
	
	@OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<Product> product;

	public SubCategory() {

	}
	
	public SubCategory(Boolean isActivate, String subCategoryName, Category category, Set<Product> product) {
		super();
		this.isActivate = isActivate;
		this.subCategoryName = subCategoryName;
		this.category = category;
		this.product = product;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Boolean getIsActivate() {
		return isActivate;
	}

	public void setIsActivate(Boolean isActivate) {
		this.isActivate = isActivate;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

}
