package com.projects.eShopping.service;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.projects.eShopping.dto.FindMyListingsReqDTO;
import com.projects.eShopping.model.Product;
import com.projects.eShopping.model.User;
import com.projects.eShopping.repo.ProductRepo;
import com.projects.eShopping.repo.UserRepo;

@Service
public class ProductService {

	private ProductRepo productRepo;
	private UserRepo userRepo;
	
	
	public ProductService(ProductRepo productRepo, UserRepo userRepo) {
		super();
		this.productRepo = productRepo;
		this.userRepo = userRepo;
	}


	public Page<Product> findMyListings(FindMyListingsReqDTO reqDTO) {
		User seller = userRepo.findByUsername(reqDTO.getUsername());
		if(seller != null)
			return productRepo.findBySellerUsername(seller.getUsername(), PageRequest.of(reqDTO.getPageNumber(), reqDTO.getSize()));
		else
			return null;
	}
}
