package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.ProductImage;
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    
	@Query(value = "select * from product_image where product_id = ?1 ", nativeQuery = true)
	List<ProductImage> findByProductId(Long productId);

}
