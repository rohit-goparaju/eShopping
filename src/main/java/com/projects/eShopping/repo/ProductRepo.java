package com.projects.eShopping.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.eShopping.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

}
