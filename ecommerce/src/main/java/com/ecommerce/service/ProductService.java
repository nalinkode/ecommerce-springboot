package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Product;

public interface ProductService {

	void addProduct(Product product);

	boolean getProductById(Long productId);

	List<Product> getAllProduct();

	void deleteById(Long productId);

}
