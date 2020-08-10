package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.model.Category;

public interface CategoryService {

	public void addCategory(Category category);

	public List<Category> getAllCategory();

	public boolean getCategoryById(Long categoryId);

	public void deleteById(Long categoryId);

}
