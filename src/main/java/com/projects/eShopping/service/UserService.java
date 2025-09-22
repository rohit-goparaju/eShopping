package com.projects.eShopping.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projects.eShopping.dto.AddUserRequestDTO;
import com.projects.eShopping.dto.AddUserResponseDTO;
import com.projects.eShopping.dto.UserLoginRequestDTO;
import com.projects.eShopping.dto.UserLoginResponseDTO;
import com.projects.eShopping.dto.UserResponseDTO;
import com.projects.eShopping.model.User;
import com.projects.eShopping.repo.UserRepo;

@Service
public class UserService {

	private UserRepo repo;
	private PasswordEncoder encoder;
	private JWTService jwtService;
	private AuthenticationManager authManager;
	

	public UserService(UserRepo repo, PasswordEncoder encoder, JWTService jwtService,
			AuthenticationManager authManager) {
		super();
		this.repo = repo;
		this.encoder = encoder;
		this.jwtService = jwtService;
		this.authManager = authManager;
	}

	public AddUserResponseDTO addUser(AddUserRequestDTO reqDTO) {
		if(repo.findByUsername(reqDTO.getUsername())!= null) {
			return null;
		}

		User newUser = new User();
		newUser.setUsername(reqDTO.getUsername());
		newUser.setPassword(encoder.encode(reqDTO.getPassword()));

		User savedUser = repo.save(newUser);

		return savedUser != null ? new AddUserResponseDTO(savedUser.getUsername(), jwtService.generateToken(savedUser.getUsername())) : null;
	}

	public List<UserResponseDTO> findAllUsers() {
		List<User> users = repo.findAll();		
		return users != null ? users.stream().map(user->new UserResponseDTO(user.getUsername())).toList() : new ArrayList<UserResponseDTO>();
	}

	public UserLoginResponseDTO login(UserLoginRequestDTO loginDTO) {
		Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		if(auth.isAuthenticated()) {
			User existingUser = repo.findByUsername(loginDTO.getUsername());		
			return existingUser != null && encoder.matches(loginDTO.getPassword(), existingUser.getPassword()) ? new UserLoginResponseDTO(existingUser.getUsername(), jwtService.generateToken(existingUser.getUsername())) : null;			
		}
		else
			return null;
	}

}
