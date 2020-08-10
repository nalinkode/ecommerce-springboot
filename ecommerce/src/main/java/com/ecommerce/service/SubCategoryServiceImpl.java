package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Category;
import com.ecommerce.model.SubCategory;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    
	@Autowired
	private SubCategoryRepository subCategoryRepo;
	
	@Autowired
	private CategoryRepository categoryRepository;


	@Override
	public void addSubcategory(SubCategory subcategory) {
		Optional<Category> cat = categoryRepository.findById(subcategory.getCategory().getCategoryId());
		if(cat != null) {
		   subCategoryRepo.save(subcategory);
		}
	}

	@Override
	public List<SubCategory> getAllSubcategory() {
		return subCategoryRepo.findByCategory();
	}
	

	@Override
	public void deleteById(Long subCategoryId) {
		subCategoryRepo.deleteById(subCategoryId);

	}

	@Override
	public boolean getSubCategoryById(Long subCategoryId) {
		return subCategoryRepo.findById(subCategoryId) != null;
	}

}
