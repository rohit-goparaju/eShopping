package com.projects.eShopping.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projects.eShopping.dto.AddListingReqDTO;
import com.projects.eShopping.dto.AddUserRequestDTO;
import com.projects.eShopping.dto.AddUserResponseDTO;
import com.projects.eShopping.dto.ChangePasswordReqDTO;
import com.projects.eShopping.dto.DeleteAccountReqDTO;
import com.projects.eShopping.dto.FindMyListingsReqDTO;
import com.projects.eShopping.dto.ResetPasswordReqDTO;
import com.projects.eShopping.dto.ResetPasswordResDTO;
import com.projects.eShopping.dto.SecurityDetailsReqDTO;
import com.projects.eShopping.dto.SecurityDetailsResDTO;
import com.projects.eShopping.dto.UserLoginRequestDTO;
import com.projects.eShopping.dto.UserLoginResponseDTO;
import com.projects.eShopping.dto.UserResponseDTO;
import com.projects.eShopping.enums.RequestStatus;
import com.projects.eShopping.model.Product;
import com.projects.eShopping.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class HomeController {
	
	private UserService userService;

	public HomeController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/")
	public String greet() {
		return "Welcome to eShopping";
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<AddUserResponseDTO> addUser(@Valid @RequestBody AddUserRequestDTO reqDTO) {
		AddUserResponseDTO resDTO = userService.addUser(reqDTO);
		return resDTO == null? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resDTO) : ResponseEntity.ok(resDTO);
	}
	
	@GetMapping("/findAllUsers")
	public ResponseEntity<List<UserResponseDTO>> findAllUsers(){
		return ResponseEntity.ok(userService.findAllUsers());
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginResponseDTO> login(@Valid @RequestBody UserLoginRequestDTO loginDTO){
		UserLoginResponseDTO resDTO = userService.login(loginDTO);
		return resDTO != null ? ResponseEntity.ok(resDTO) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<RequestStatus> changePassword(@Valid @RequestBody ChangePasswordReqDTO reqDTO){
		RequestStatus status = userService.changePassword(reqDTO);
		return status == RequestStatus.SUCCESS ? ResponseEntity.ok(status) : ResponseEntity.badRequest().body(status);
	}
	
	@PostMapping("/forgotPassword/getSecurityDetails")
	public ResponseEntity<SecurityDetailsResDTO> getSecurityDetails(@Valid @RequestBody SecurityDetailsReqDTO reqDTO){
		SecurityDetailsResDTO securityDetails = userService.getSecurityDetails(reqDTO);
		return securityDetails != null? ResponseEntity.ok(securityDetails) : ResponseEntity.badRequest().body(securityDetails);
	}
	
	@PutMapping("/forgotPassword/resetPassword")
	public ResponseEntity<ResetPasswordResDTO> resetPassword(@Valid @RequestBody ResetPasswordReqDTO reqDTO){
		ResetPasswordResDTO resDTO = userService.resetPassword(reqDTO);
		return resDTO != null ? ResponseEntity.ok(resDTO) : ResponseEntity.badRequest().body(resDTO);
	}
	
	@PutMapping("/deleteAccount")
	public ResponseEntity<RequestStatus> deleteAccount(@Valid @RequestBody DeleteAccountReqDTO reqDTO){
		RequestStatus status = userService.deleteAccount(reqDTO);
		return status == RequestStatus.SUCCESS ? ResponseEntity.ok(status) : ResponseEntity.badRequest().body(status);
	}
	
	@PostMapping("/addListing")
	public ResponseEntity<RequestStatus> addListing(@Valid @RequestPart(name = "productDetails") AddListingReqDTO reqDTO, @RequestPart(name = "productImage") MultipartFile productImage) throws IOException{
		RequestStatus status = userService.addListing(reqDTO, productImage);
		return status == RequestStatus.SUCCESS ? ResponseEntity.ok(status) : ResponseEntity.badRequest().body(status);
	}
}
