package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.model.Product;

public interface ProductService {

	void addProduct(Product product);

	boolean isProductById(Long productId);

	List<Product> getAllProduct();

	void deleteById(Long productId);
	
	Product getProductById(Long productId);

}
