package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    
	@Query(value = "select * from category c, sub_category s where c.category_id = s.category_id", nativeQuery = true)
	List<SubCategory> findByCategory();
 
}
