package com.projects.eShopping.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.eShopping.dto.FindMyListingsReqDTO;
import com.projects.eShopping.model.Product;
import com.projects.eShopping.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;
	

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@PostMapping("/findMyListings")
	public ResponseEntity<Page<Product>> findMyListings(@Valid @RequestBody FindMyListingsReqDTO reqDTO){
		Page<Product> myListings = productService.findMyListings(reqDTO);
		return ResponseEntity.ok(myListings);
	}

	@GetMapping("/findAllListings")
	public ResponseEntity<Page<Product>> findAllListings(@RequestParam(name = "size", required = true) int size, @RequestParam(name="page", required=true) int page, @RequestParam(name="search", defaultValue = "") String search){
		return ResponseEntity.ok(productService.findAllListings(size, page, search));	
	}
	
}

