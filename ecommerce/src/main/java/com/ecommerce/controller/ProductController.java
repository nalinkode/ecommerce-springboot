package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Product;
import com.ecommerce.model.SubCategory;
import com.ecommerce.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/add")
	private ResponseEntity<Product> addSubcategory(@RequestBody Product product) {
		HttpHeaders header = new HttpHeaders();
		if (product.getProductId() == null) {
			productService.addProduct(product);
		}
		header.add("desc", "One Product Added");
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
	}

	@PutMapping("/update")
	private ResponseEntity<Product> updateCategory(@RequestBody Product product) {
		HttpHeaders header = new HttpHeaders();
		boolean isProduct = productService.getProductById(product.getProductId());
		if (isProduct) {
			productService.addProduct(product);
		}
		header.add("desc", "One Product Updated");
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
	}

	@GetMapping("/all")
	private ResponseEntity<List<Product>> getAllSubcategory() {
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "List of category");
		List<Product> productList = productService.getAllProduct();
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(productList);
	}

	@DeleteMapping("/delete/{productId}")
	private ResponseEntity<Void> deleteById(@PathVariable Long productId) {
		boolean isProduct = productService.getProductById(productId);
		try {
			if (isProduct) {
				productService.deleteById(productId);
			}
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
