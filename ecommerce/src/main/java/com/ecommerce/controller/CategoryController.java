package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;

//@CrossOrigin(origins = "https://ecommerce-cashev.stackblitz.io", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/add")
	private ResponseEntity<Category> addCategory(@RequestBody Category category) {
		HttpHeaders header = new HttpHeaders();
		if (category.getCategoryId() == null) {
			categoryService.addCategory(category);
		}
		header.add("desc", "One Category Added");
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
	}

	@PutMapping("/update")
	private ResponseEntity<Category> updateCategory(@RequestBody Category category) {
		HttpHeaders header = new HttpHeaders();
		boolean cat = categoryService.getCategoryById(category.getCategoryId());
		if (cat) {
			categoryService.addCategory(category);
		}
		header.add("desc", "One Category updated");
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
	}

	@GetMapping("/all")
	private ResponseEntity<List<Category>> getAllCategory() {
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "List of category");
		List<Category> category = categoryService.getAllCategory();
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(category);
	}

	@DeleteMapping("/delete/{categoryId}")
	private ResponseEntity<Void> deleteById(@PathVariable Long categoryId) {
		boolean isCategory = categoryService.getCategoryById(categoryId);
		try {
			categoryService.deleteById(categoryId);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();

		}

	}

}
