package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Product;
import com.ecommerce.model.SubCategory;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.SubCategoryRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private SubCategoryRepository subCategoryRepo;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addProduct(Product product) {
		if (product.getProductId() == null) {
			Optional<SubCategory> subCategoryObj = subCategoryRepo
					.findById(product.getSubCategory().getSubCategoryId());
			if (subCategoryObj != null) {
				productRepository.save(product);
			}
		}
		productRepository.save(product);
	}

	@Override
	public boolean isProductById(Long productId) {
		return productRepository.findById(productId) != null;
	}

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public void deleteById(Long productId) {
		productRepository.deleteById(productId);
	}

	@Override
	public Product getProductById(Long productId) {
		return productRepository.findByProductId(productId);
	}

}
