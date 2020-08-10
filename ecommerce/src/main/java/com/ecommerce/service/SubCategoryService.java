package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.SubCategory;

public interface SubCategoryService {

	void addSubcategory(SubCategory subcategory);

	List<SubCategory> getAllSubcategory();

	void deleteById(Long subCategoryId);

	boolean getSubCategoryById(Long subCategoryId);


}
