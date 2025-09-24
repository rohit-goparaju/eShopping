package com.projects.eShopping.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projects.eShopping.dto.AddListingReqDTO;
import com.projects.eShopping.dto.AddUserRequestDTO;
import com.projects.eShopping.dto.AddUserResponseDTO;
import com.projects.eShopping.dto.ChangePasswordReqDTO;
import com.projects.eShopping.dto.ResetPasswordReqDTO;
import com.projects.eShopping.dto.ResetPasswordResDTO;
import com.projects.eShopping.dto.SecurityDetailsReqDTO;
import com.projects.eShopping.dto.SecurityDetailsResDTO;
import com.projects.eShopping.dto.UserLoginRequestDTO;
import com.projects.eShopping.dto.UserLoginResponseDTO;
import com.projects.eShopping.dto.UserResponseDTO;
import com.projects.eShopping.enums.RequestStatus;
import com.projects.eShopping.model.Product;
import com.projects.eShopping.model.User;
import com.projects.eShopping.repo.UserRepo;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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
		newUser.setSecurityQuestion(reqDTO.getSecurityQuestion().trim().toLowerCase());
		newUser.setSecurityAnswer(reqDTO.getSecurityAnswer().trim().toLowerCase());

		User savedUser = repo.save(newUser);

		return savedUser != null ? new AddUserResponseDTO(savedUser.getUsername(), jwtService.generateToken(savedUser.getUsername())) : null;
	}

	public List<UserResponseDTO> findAllUsers() {
		List<User> users = repo.findAll();		
		return users != null ? users.stream().map(user->new UserResponseDTO(user.getUsername())).toList() : new ArrayList<UserResponseDTO>();
	}

	public UserLoginResponseDTO login(UserLoginRequestDTO loginDTO) {
		User savedUser = repo.findByUsername(loginDTO.getUsername());
		if(savedUser != null) {			
			Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
			if(auth.isAuthenticated()) {
				User existingUser = repo.findByUsername(loginDTO.getUsername());		
				return existingUser != null && encoder.matches(loginDTO.getPassword(), existingUser.getPassword()) ? new UserLoginResponseDTO(existingUser.getUsername(), jwtService.generateToken(existingUser.getUsername())) : null;			
			}
			else
				return null;
		}
		else
			return null;
	}

	public RequestStatus changePassword(@Valid ChangePasswordReqDTO reqDTO) {
		User savedUser = repo.findByUsername(reqDTO.getUsername());

		if(savedUser != null) {
			if(encoder.matches(reqDTO.getOldPassword(), savedUser.getPassword())) {
				if(!encoder.matches(reqDTO.getNewPassword(), savedUser.getPassword())) {
					savedUser.setPassword(encoder.encode(reqDTO.getNewPassword()));					
					repo.save(savedUser);
				}
				return RequestStatus.SUCCESS;
			}
		}
		return RequestStatus.FAILED;
	}

	public SecurityDetailsResDTO getSecurityDetails(@Valid SecurityDetailsReqDTO reqDTO) {
		User savedUser = repo.findByUsername(reqDTO.getUsername());
		SecurityDetailsResDTO securityDetails = null;
		if(savedUser != null) {
			securityDetails = new SecurityDetailsResDTO(savedUser.getSecurityQuestion(), savedUser.getSecurityAnswer());
		}
		return securityDetails;
	}

	public ResetPasswordResDTO resetPassword(@Valid ResetPasswordReqDTO reqDTO) {
		User savedUser = repo.findByUsername(reqDTO.getUsername());
		ResetPasswordResDTO resDTO = null;
		if(savedUser != null) {
			if(!encoder.matches(reqDTO.getNewPassword(), savedUser.getPassword())) {
				savedUser.setPassword(encoder.encode(reqDTO.getNewPassword()));
				repo.save(savedUser);
			}
			resDTO = new ResetPasswordResDTO(savedUser.getUsername(), jwtService.generateToken(savedUser.getUsername()));
		}
		return resDTO;
	}

	public RequestStatus deleteAccount(@Valid SecurityDetailsReqDTO reqDTO) {
		User savedUser = repo.findByUsername(reqDTO.getUsername());
		
		if(savedUser != null) {
			repo.delete(savedUser);
			return RequestStatus.SUCCESS;
		}
		
		return RequestStatus.FAILED;
	}

	@Transactional
	public RequestStatus addListing(@Valid AddListingReqDTO reqDTO, MultipartFile productImage) throws IOException {
		// TODO Auto-generated method stub
		User savedUser = repo.findByUsername(reqDTO.getSellerUsername());
		if(savedUser != null) {
			if(savedUser.getListings() == null) {
				savedUser.setListings(new LinkedList<>());
			}
			Product product = new Product();
			product.setName(reqDTO.getName().trim().toLowerCase());
			product.setSeller(savedUser);
			product.setDescription(reqDTO.getDescription().trim().toLowerCase());
			product.setPrice(reqDTO.getPrice());
			byte[] imageBytes = productImage.getBytes();
			product.setProductImage(imageBytes);
			
			savedUser.getListings().add(product);
			
			repo.save(savedUser);
			
			return RequestStatus.SUCCESS;
		}
		return RequestStatus.FAILED;
	}
}
