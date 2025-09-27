package com.projects.eShopping.repo;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projects.eShopping.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	public Page<Product> findBySellerUsername(String sellerUsername, Pageable pageable);
	public Page<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String nameTerm, String descriptionTerm, Pageable pageable);
	
	@Query("""
			SELECT p FROM Product p 
			WHERE p.sellerUsername = :sellerUsername
			AND (
				(LOWER(p.name) LIKE (CONCAT('%', LOWER(:searchTerm), '%')))
				OR
				(LOWER(p.description) LIKE (CONCAT('%',LOWER(:searchTerm), '%')))
			)
			""")
	public Page<Product> findSpecificSellerProducts(@Param("sellerUsername") String sellerUsername, @Param("searchTerm") String searchTerm, Pageable pageable);

	public List<Product> findByBuyerUsername(String buyerUsername);
}
