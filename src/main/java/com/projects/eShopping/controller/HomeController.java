package com.projects.eShopping.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.eShopping.dto.AddUserRequestDTO;
import com.projects.eShopping.dto.AddUserResponseDTO;
import com.projects.eShopping.dto.UserLoginRequestDTO;
import com.projects.eShopping.dto.UserLoginResponseDTO;
import com.projects.eShopping.dto.UserResponseDTO;
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
	
}
