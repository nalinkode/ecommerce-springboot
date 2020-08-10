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

import com.ecommerce.model.SubCategory;
import com.ecommerce.service.SubCategoryService;

//@CrossOrigin(origins = "https://ecommerce-cashev.stackblitz.io", maxAge = 3600)
@RestController
@RequestMapping("/subcategory")
public class SubcategoryController {
	@Autowired
	private SubCategoryService subCategoryService;

	@PostMapping("/add")
	private ResponseEntity<SubCategory> addSubcategory(@RequestBody SubCategory subcategory) {
		HttpHeaders header = new HttpHeaders();
		if (subcategory.getSubCategoryId() == null) {
			subCategoryService.addSubcategory(subcategory);
		}
		header.add("desc", "One subcategory Added");
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
	}

	@PutMapping("/update")
	private ResponseEntity<SubCategory> updateCategory(@RequestBody SubCategory subcategory) {
		HttpHeaders header = new HttpHeaders();
		boolean cat = subCategoryService.getSubCategoryById(subcategory.getSubCategoryId());
		if (cat) {
			subCategoryService.addSubcategory(subcategory);
		}
		header.add("desc", "One Subcategory updated");
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
	}

	@GetMapping("/all")
	private ResponseEntity<List<SubCategory>> getAllSubcategory() {
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "List of category");
		List<SubCategory> subcategory = subCategoryService.getAllSubcategory();
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(subcategory);
	}

	@DeleteMapping("/delete/{subCategoryId}")
	private ResponseEntity<Void> deleteById(@PathVariable Long subCategoryId) {
		boolean isSubCategory = subCategoryService.getSubCategoryById(subCategoryId);
		try {
			if (isSubCategory) {
				subCategoryService.deleteById(subCategoryId);
			}
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();

		}

	}

}
