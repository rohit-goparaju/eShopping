package com.projects.eShopping.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		{
			Pageable pageable = PageRequest.of(reqDTO.getPageNumber(), reqDTO.getSize(), Sort.by("name").ascending());
			if(reqDTO.getSearch() == null || reqDTO.getSearch().isEmpty() || reqDTO.getSearch().isBlank())
				return productRepo.findBySellerUsername(seller.getUsername(), pageable);
			else
				return productRepo.findSpecificSellerProducts(seller.getUsername(), reqDTO.getSearch(), pageable);
		}
		else
			return null;
	}


	public Page<Product> findAllListings(int size, int page, String search) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
		if(search == null || search.isBlank() || search.isEmpty())
			return productRepo.findAll(pageable);
		else
			return productRepo.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(search, search, pageable);
	}


	
}
