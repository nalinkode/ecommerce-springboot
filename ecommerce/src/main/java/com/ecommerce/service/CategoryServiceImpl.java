package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void addCategory(Category category) {
		try {
			categoryRepository.save(category);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public boolean getCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId) != null;
	}

	@Override
	public void deleteById(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}

}
